package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import DataObjects.DataObjectMessageToUser;
import DataObjects.DateConvert;

/**
 * check validity of the current member dates
 * @author Gal
 *
 */
public class MessageCheckValidMember extends Message {

	/**
	 * checks members that did check in but still didn't do check out and check if 14 days passed and fine them.
	 */
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			
			oneWeekNoticeAndValidCheck();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

	public void oneWeekNoticeAndValidCheck() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM members;");
		ResultSet rs = ps.executeQuery();
		while (rs.next())
		{
			java.util.Date startDate = DateConvert.fixDate(rs.getDate(7));
			java.util.Date currentDate = DateConvert.fixDate(DateConvert.getCurrentSqlDate());
			
			if (TimeUnit.MINUTES.toDays(DateConvert.timeDifference(currentDate, startDate)) == 21)
			{
				DataObjectMessageToUser domtu = new DataObjectMessageToUser("0",rs.getString(1),"Alert! your membership will expire in 1 week.");
				MessageSendMessage sm = new MessageSendMessage(domtu);
				sm.doAction();
			}
			else if (TimeUnit.MINUTES.toDays(DateConvert.timeDifference(currentDate, startDate)) == 28)
			{
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO customers (id,carID,FirstName,LastName,email) VALUES (?,?,?,?,?);");
				ps1.setString(1, rs.getString(3));
				ps1.setString(2, rs.getString(2));
				ps1.setString(3, rs.getString(4));
				ps1.setString(4, rs.getString(5));
				ps1.setString(5, rs.getString(6));
				ps1.executeUpdate();
				ps1.close();
				
				ps1 = con.prepareStatement("DELETE FROM members WHERE memberID=? and carID=?;");
				ps1.setString(1, rs.getString(1));
				ps1.setString(2, rs.getString(2));
				ps.close();
				ps1.executeUpdate();
				ps1.close();
			}
		}
	}
}
