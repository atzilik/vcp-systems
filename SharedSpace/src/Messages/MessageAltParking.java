package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class MessageAltParking extends Message {
	private String[] details;  


	public MessageAltParking(String[] details) {
		this.details = details;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("UPDATE parkinglots SET alternative=? WHERE parkingLotID=?;");
			ps.setString(1, details[1]);
			ps.setString(2, details[0]);
			ps.executeUpdate();
			return new MessageAltParkingReply(details[2]);
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
}