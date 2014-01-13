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

public class MessageCheckout extends Message {
	private Customer customer;
	private int parkingLotID;
	Time estCheckOutTime;
	Date estCheckOutDate;
	
	public MessageCheckout(Customer customer, int parkingLotID){
		this.customer = customer;
		this.parkingLotID = parkingLotID;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
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
				return new MessageCheckoutReply(customer);  // customer didn't check in
			}
			rs.next();
			Date CinDate = rs.getDate(4);
			Time CinTime = rs.getTime(5);
			estCheckOutTime = rs.getTime(7);
			estCheckOutDate = rs.getDate(6);
			ps.close();
			
			//update details in table
			ps = con.prepareStatement("UPDATE parking_control SET realCotDate=?,realCotHour=? where customerId=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
			Date realCotDate = DateConvert.getCurrentSqlDate();
			Time realCotTime = DateConvert.getCurrentSqlTime();
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
			
			
			//update customer bill
			double bill = 0;
			//if its std customer then update reservation id as well
			if (customer instanceof STDCustomer)
			{
				ps = con.prepareStatement("INSERT INTO customer_bill (customerID,carID,date,time,reservationID,sum) VALUES (?,?,?,?,?,?);");
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
				rs = ps1.executeQuery();
				rs.next();
				ps.setString(5, rs.getString(1));
				boolean inAdvance = rs.getBoolean(9);
				ps1.close();
				
				//get appropriate rate
				ps1 = con.prepareStatement("SELECT OccasionalRAte,ReservedRate FROM parkinglots WHERE parkingLotID=?;");
				ps1.setInt(1, parkingLotID);
				rs = ps1.executeQuery();
				rs.next();
				double rate;
				double fineRate = 0;
				double diff = DateConvert.timeDifference(realCotDate, realCotTime, estCheckOutDate, estCheckOutTime);
				if (inAdvance)
				{
					if (diff > 0)
						fineRate = rs.getDouble(2);
					rate = rs.getDouble(2);
				}
				else
				{
					rate = rs.getDouble(1);
				}
				bill = DateConvert.timeDifference(estCheckOutDate, estCheckOutTime, CinDate, CinTime) * (rate / 60);
				if ( fineRate != 0)
					bill = bill + diff * (fineRate / 60);
				ps.setDouble(6, bill);
				ps.executeUpdate();
				ps.close();
			}
//			else /need to check what to do when STDMember delay 
//			{
//				ps = con.prepareStatement("INSERT TO customer_bill (customerID,carID,date,time,sum) VALUES (?,?,?,?,?);");
//				ps.setString(1, ((STDMember)customer).getMemberId());
//			}
			
						
			//get the car coordinates to help robot find it quickly
			ps = con.prepareStatement("SELECT * FROM parkinglot_map where carNum=?;");
			ps.setString(1, customer.getCarId());
			rs = ps.executeQuery();
			rs.next();
			MessageCheckoutReply cor = new MessageCheckoutReply(customer, parkingLotID, bill, rs.getInt(2), rs.getInt(3), rs.getInt(4));
			ps.close();
			return cor;
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
