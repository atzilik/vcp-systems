package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageSTDToFullRegister extends MessageMemberRegister {

	public MessageSTDToFullRegister(String[] details, String type) {
		super(details, type);
		// TODO Auto-generated constructor stub
	}
	
	public Message doAction() {
		con = this.sqlConnection.getConnection();
		try{
			//check if member is already a full member
			PreparedStatement ps = con.prepareStatement("SELECT * FROM members WHERE memberID=? and carID=? and type=3;");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			ResultSet rs = ps.executeQuery();
			
			if (rs.isBeforeFirst())
			{
				ps.close();
				return new MessageSTDToFullRegisterReply();
			}
			
			ps.close();
			//check if customer doesn't exist in table
			ps = con.prepareStatement("SELECT * FROM members WHERE memberID=? and carID=?;");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			rs = ps.executeQuery();
			
			if (rs.isBeforeFirst() == false)
			{
				ps.close();
				//check if its stdcustomer, delete if it is
				ps = con.prepareStatement("SELECT * FROM customers WHERE id=? and carID=?;");
				ps.setString(1, details[2]);
				ps.setString(2, details[1]);
				rs = ps.executeQuery();


				if (rs.next())
				{
					//deleting existing customer
					ps.close();
					ps = con.prepareStatement("DELETE FROM customers WHERE id=? and carID=?;");
					ps.setString(1, details[2]);
					ps.setString(2, details[1]);
					ps.executeUpdate();
					ps.close();
				}
				ps.close();
				
				//insert as a new member
				ps = con.prepareStatement("INSERT INTO members (memberID,carID,id,fName,lName,email,startDate,type) VALUES(?,?,?,?,?,?,?,?);");
				for (int i = 0 ; i < details.length; i++)
				{
					ps.setString(i + 1, details[i]);
				}
				ps.executeUpdate();
				ps.close();
				return new MessageSTDToFullRegisterReply(details[0], details[1]);
			}
			//customer is stdmember, upgrade to full
			else
			{
				ps.close();
				ps = con.prepareStatement("UPDATE vcp.members SET StartDate=?,ParkingLotId=null,StandardCheckOut=null,type=3 WHERE memberID=? and carID=?;");
				ps.setString(1, details[6]);
				ps.setString(2, details[0]);
				ps.setString(3, details[1]);
				ps.executeUpdate();
				
				ps.close();
				return new MessageSTDToFullRegisterReply(details[0], details[1]);
			}
			
			
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
