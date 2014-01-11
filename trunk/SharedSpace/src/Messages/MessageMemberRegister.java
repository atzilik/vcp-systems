package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import DataObjects.DateConvert;

public class MessageMemberRegister extends Message {
	protected String[] details;
	protected String type;

	public MessageMemberRegister(String[] details, String type){
		this.details = details;
		this.type = type;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			//if car already registered is a member
			PreparedStatement ps = con.prepareStatement("SELECT * FROM members WHERE carID=?;");
			ps.setString(1, details[1]);
			ResultSet rs = ps.executeQuery();

			if (rs.isBeforeFirst())
			{
				ps.close();
				return new MessageMemberRegisterReply();
			}

//			// if memberID already exist
//			ps = con.prepareStatement("SELECT * FROM members WHERE memberID=?;");
//			ps.setString(1, details[0]);
//			rs = ps.executeQuery();
//			
//			if (rs.next())
//				details[0] = rs.getString(1);
			//			if (rs.next())
			//				//existing inactive member
			//			{
			//				//update inactive member
			//				PreparedStatement ps1 = con.prepareStatement("UPDATE full_members SET startDate=?, active=? WHERE memberID=?;");
			//				ps1.setString(1, details[6]);
			//				ps1.setString(2, details[8]);
			//				ps1.setString(3, details[1]);
			//				ps.executeUpdate();
			//				ps1.close();
			//				ps.close();
			//
			//				//
			//				
			//				return new MessageFullMemberRegisterReply(details[0]);
			//
			//			}

			//new member

			switch (type)
			{
			case "2": ps = con.prepareStatement("INSERT INTO members (memberID,carID,id,fName,lName,email,startDate,ParkingLotId,StandardCheckOut,type) VALUES(?,?,?,?,?,?,?,?,?,?);");
			break;
			case "3": ps = con.prepareStatement("INSERT INTO members (memberID,carID,id,fName,lName,email,startDate,type) VALUES(?,?,?,?,?,?,?,?);");
			break;
			}

			
			for (int i = 0 ; i < details.length; i++)
			{
				ps.setString(i + 1, details[i]);
			}
			ps.executeUpdate();
			ps.close();

			//update customer bill
			ps = con.prepareStatement("INSERT INTO customer_bill (customerID,carID,date,time,sum) VALUES (?,?,?,?,?);");
			ps.setString(1, details[2]);
			ps.setString(2, details[1]);
			ps.setDate(3, DateConvert.getCurrentSqlDate());
			ps.setTime(4, DateConvert.getCurrentSqlTime());
			//get the rate
			PreparedStatement ps1 = con.prepareStatement("SELECT StandardRate,FullRate FROM parkinglots WHERE parkingLotID=?;");
			if (type.equals("2"))
			{
				ps1.setString(1, details[7]);
			}
			else
			{
				ps1.setInt(1, 0);
			}
			rs = ps1.executeQuery();
			rs.next();
			double rate;
			if (type.equals("2"))
			{
				rate = rs.getDouble(1);
			}
			else
			{
				rate = rs.getDouble(2);
			}
			ps1.close();
			ps.setDouble(5, rate);
			ps.executeUpdate();
			ps.close();
			
			//check if its an existing customer
			ps = con.prepareStatement("SELECT * FROM customers WHERE id=? and carID=?;");
			ps.setString(1, details[2]);
			ps.setString(2, details[1]);
			rs = ps.executeQuery();


			if (rs.next())
			{
				//deleting existing customer
				ps = con.prepareStatement("DELETE FROM customers WHERE id=? and carID=?;");
				ps.setString(1, details[2]);
				ps.setString(2, details[1]);
				ps.executeUpdate();
				ps.close();
			}
			ps.close();
			return new MessageMemberRegisterReply(details[0],details[1], rate);

		}catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}

}
