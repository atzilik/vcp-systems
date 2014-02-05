package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import DataObjects.Customer;
import DataObjects.DateConvert;
import DataObjects.FullMember;
import DataObjects.STDCustomer;
import DataObjects.STDMember;
/**
 * handles a check out of a customer
 * @author Gal
 *
 */
public class MessageCheckout extends Message {
	/**
	 * customer instance
	 */
	private Customer customer;
	private int parkingLotID;
	private Time estCheckOutTime;
	private Date estCheckOutDate;
	private Date realCotDate;
	private Time realCotTime;
	private Date CinDate;
	private Time CinTime;
	private double bill = 0;
	public MessageCheckout(Customer customer, int parkingLotID){
		this.customer = customer;
		this.parkingLotID = parkingLotID;
		con = sqlConnection.getConnection();
	}
	/**
	 * check first that the customer actually did a check in before trying to check out.
	 * update DB with check out details and calculate customer bill.
	 */
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		try{
			if (checkPreviousCheckIn() == false)
			{
				return new MessageCheckoutReply(customer);
			}
			updateCheckOut();
			updateCustomerBill();
			
			//get the car coordinates to help robot find it quickly
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglot_map where carNum=?;");
			ps.setString(1, customer.getCarId());
			ResultSet rs = ps.executeQuery();
			rs.next();
			MessageCheckoutReply cor = new MessageCheckoutReply(customer, parkingLotID, bill, rs.getInt(2), rs.getInt(3), rs.getInt(4));
			ps.close();
			return cor;
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public boolean checkPreviousCheckIn() throws SQLException{
		//check if customer did check in
		PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where customerID=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
		if (customer instanceof STDCustomer)
		{
			ps.setString(1, customer.getId());
		}
		else if (customer instanceof STDMember)
		{
			ps.setString(1, ((STDMember)customer).getMemberId());
		}
		else
		{
			ps.setString(1, ((FullMember)customer).getMemberId());
		}
		ps.setString(2, customer.getCarId());
		ps.setInt(3, parkingLotID);
		ResultSet rs = ps.executeQuery();
		if (rs.isBeforeFirst() == false)
		{
			ps.close();
			return false;  // customer didn't check in
		}
		rs.next();
		CinDate = rs.getDate(4);
		CinTime = rs.getTime(5);
		estCheckOutTime = rs.getTime(7);
		estCheckOutDate = rs.getDate(6);
		ps.close();
		return true;
	}
	
	public void updateCheckOut() throws SQLException{
		//update details in table
		PreparedStatement ps = con.prepareStatement("UPDATE parking_control SET realCotDate=?,realCotHour=? where customerId=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
		realCotDate = DateConvert.getCurrentSqlDate();
		realCotTime = DateConvert.getCurrentSqlTime();
		ps.setDate(1, realCotDate);
		ps.setTime(2, realCotTime);
		if (customer instanceof STDCustomer)
		{
			ps.setString(3, customer.getId());
			ps.setString(4, customer.getCarId());
		}
		else if (customer instanceof STDMember)
		{
			ps.setString(3, ((STDMember) customer).getMemberId());
			ps.setString(4, customer.getCarId());
		}
		else
		{
			ps.setString(3, ((FullMember) customer).getMemberId());
			ps.setString(4, customer.getCarId());
		}
		ps.setInt(5, parkingLotID);
		ps.executeUpdate();
		ps.close();
	}
	
	public void updateCustomerBill() throws SQLException{
		//update customer bill
		//if its std customer then update reservation id as well
		if (customer instanceof STDCustomer)
		{
			PreparedStatement ps = con.prepareStatement("INSERT INTO customer_bill (customerID,carID,date,time,reservationID,sum) VALUES (?,?,?,?,?,?);");
			ps.setString(1, customer.getId());
			ps.setString(2, customer.getCarId());
			ps.setDate(3, DateConvert.getCurrentSqlDate());
			ps.setTime(4, DateConvert.getCurrentSqlTime());
			//get reservation id and check if it is reservation in advance
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM reservations WHERE carId=? and customerId=? and parkingLotId=? and estCinDate=? and estCinHour=?;");
			ps1.setString(1, customer.getCarId());
			ps1.setString(2, customer.getId());
			ps1.setInt(3, parkingLotID);
			ps1.setDate(4, CinDate);
			ps1.setTime(5, CinTime);
			ResultSet rs = ps1.executeQuery();
			rs.next();
			ps.setString(5, rs.getString(1));
			boolean inAdvance = rs.getBoolean(9);
			ps1.close();
			
			bill = calculateBill(inAdvance);
			ps.setDouble(6, bill);
			ps.executeUpdate();
			ps.close();
		}
	}
	
	public double getRate(boolean inAdvance) throws SQLException{
		//get appropriate rate
		PreparedStatement ps1 = con.prepareStatement("SELECT OccasionalRAte,ReservedRate FROM parkinglots WHERE parkingLotID=?;");
		ps1.setInt(1, parkingLotID);
		ResultSet rs = ps1.executeQuery();
		rs.next();
		if (inAdvance)
		{
			return rs.getDouble(2);
		}
		return rs.getDouble(1);
	}
	
	public double getFine(double rate, Date date1, Time time1, Date date2, Time time2){
		double minutesLate = getMinutesDifference(date1, time1, date2, time2);
		return minutesLate * 1.2 * (rate / 60);
	}
	
	public long getMinutesDifference(Date date1, Time time1, Date date2, Time time2){
		return DateConvert.timeDifference(date1, time1, date2, time2);
	}
	
	public double calculateBill(boolean inAdvance) throws SQLException{
		return (getMinutesDifference(estCheckOutDate, estCheckOutTime, CinDate, CinTime) * getRate(inAdvance) / 60) + getFine(getRate(inAdvance), realCotDate, realCotTime, estCheckOutDate, estCheckOutTime);
	}
	
	
	public Time getEstCheckOutTime() {
		return estCheckOutTime;
	}
	public void setEstCheckOutTime(Time estCheckOutTime) {
		this.estCheckOutTime = estCheckOutTime;
	}
	public Date getEstCheckOutDate() {
		return estCheckOutDate;
	}
	public void setEstCheckOutDate(Date estCheckOutDate) {
		this.estCheckOutDate = estCheckOutDate;
	}
	public Date getRealCotDate() {
		return realCotDate;
	}
	public void setRealCotDate(Date realCotDate) {
		this.realCotDate = realCotDate;
	}
	public Time getRealCotTime() {
		return realCotTime;
	}
	public void setRealCotTime(Time realCotTime) {
		this.realCotTime = realCotTime;
	}
	public Date getCinDate() {
		return CinDate;
	}
	public void setCinDate(Date cinDate) {
		CinDate = cinDate;
	}
	public Time getCinTime() {
		return CinTime;
	}
	public void setCinTime(Time cinTime) {
		CinTime = cinTime;
	}

	
}
