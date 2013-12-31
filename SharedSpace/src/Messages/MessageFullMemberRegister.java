package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageFullMemberRegister extends Message {
	private String[] details;
	
	public MessageFullMemberRegister(String[] details){
		this.details = details;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM full_members WHERE memberID=? and carID=?;");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next())
			//existing member
			{
				if (rs.getString(8) == "1")
				{
					//already active member
					return new MessageFullMemberRegisterReply();
				}
				else
				{
					//update inactive member
					PreparedStatement ps1 = con.prepareStatement("UPDATE vcp.full_members SET startDate=?, active=? WHERE memberID=?;");
					ps1.setString(1, details[6]);
					ps1.setString(2, details[8]);
					ps1.setString(3, details[1]);
					ps.executeUpdate();
					ps1.close();
					ps.close();
					
					return new MessageFullMemberRegisterReply(details[0]);
				}
			}
			else
			//new member
			{
				PreparedStatement ps2 = con.prepareStatement("INSERT INTO full_members (memberID,carID,id,fName,lName,email,startDate,active) VALUES(?,?,?,?,?,?,?,?);");
				for (int i = 0 ; i < details.length; i++)
				{
					ps2.setString(i + 1, details[i]);
				}
				ps2.executeUpdate();
				ps2.close();
				ps.close();
				
				return new MessageFullMemberRegisterReply(details[0]);
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
