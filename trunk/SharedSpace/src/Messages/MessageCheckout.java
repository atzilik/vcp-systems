package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.Customer;
import DataObjects.DateConvert;

public class MessageCheckout extends Message {
	private Customer customer;
	private int parkingLotID;
	
	public MessageCheckout(Customer customer, int parkingLotID){
		this.customer = customer;
		this.parkingLotID = parkingLotID;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where customerId=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
			ps.setString(1, customer.getId());
			ps.setString(2, customer.getCarId());
			ps.setInt(3, parkingLotID);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() == false)
			{
				ps.close();
				return new MessageCheckoutReply(customer);
			}
			ps.close();
			ps = con.prepareStatement("UPDATE parking_control SET realCotDate=?,realCotHour=? where customerId=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
			ps.setDate(1, DateConvert.getCurrentSqlDate());
			ps.setTime(2, DateConvert.getCurrentSqlTime());
			ps.setString(3, customer.getId());
			ps.setString(4, customer.getCarId());
			ps.setInt(5, parkingLotID);
			ps.executeUpdate();
			ps.close();
			
			ps = con.prepareStatement("SELECT * FROM parkinglot_map where carNum=?;");
			ps.setString(1, customer.getCarId());
			rs = ps.executeQuery();
			rs.next();
			MessageCheckoutReply cor = new MessageCheckoutReply(customer, parkingLotID, rs.getInt(2), rs.getInt(3), rs.getInt(4));
			ps.close();
			return cor;
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
