package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class MessageCheckMemberID extends Message {



	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			while(true)
			{
				String memberID = Integer.toString(100000 + new Random().nextInt(900000));
				PreparedStatement ps = con.prepareStatement("SELECT * FROM members WHERE memberID=?;");
				ps.setString(1, memberID);
				ResultSet rs = ps.executeQuery();
				if (rs.isBeforeFirst())
				{
					ps.close();
					return new MessageCheckMemberIDReply(memberID);
				}
				ps.close();
			}
			
		}catch (SQLException e) {};
		return null;
	}

}
