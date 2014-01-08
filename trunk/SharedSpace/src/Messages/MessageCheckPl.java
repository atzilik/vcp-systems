package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.DateConvert;
import DataObjects.Reservation;
import DataObjects.STDMember;

public class MessageCheckPl extends Message{
	private String carNum;
	private String id;
	private int pl;
	private int mpl;
	Date todayDate = new java.sql.Date(new java.util.Date().getTime()); 
	public MessageCheckPl(String id, String carNum, int pl,int mpl) {
		this.carNum = carNum;
		this.id = id;
		this.pl = pl;
		this.mpl = mpl;
	}
	

	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findCheckIn(); // chack id carNum and date of today 
			if (rs == null)   // member not already park 
			{
				if (pl == mpl) // the parkinglot is his
					return new MessageCheckPlReply(0);  // his
				else
					return new MessageCheckPlReply(1);  // not his
			}
			else // there is a record on pl
			{
				if (pl == mpl) // the parkinglot is his
					return new MessageCheckPlReply(2);  // member already park today
				else
					return new MessageCheckPlReply(1);  // not his
			}
			}catch (SQLException e) {e.printStackTrace();}		
		return null;
	} // doAction
	
	public ResultSet findCheckIn() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where customerId=? and carNum=? and cinDate=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ps.setDate (3,todayDate);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next())
		{
			return rs;
		}	
		return null;
	}

}
