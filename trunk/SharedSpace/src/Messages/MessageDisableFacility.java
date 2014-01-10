package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDisableFacility extends Message {
	private int parkingLotID;

	public MessageDisableFacility(int parkingLotID) {
		this.parkingLotID = parkingLotID;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
		PreparedStatement ps = con.prepareStatement("UPDATE parkinglots SET active=0 where parkingLotID=?;");
		ps.setInt(1, parkingLotID);
		ps.executeUpdate();
		ps.close();
		return new MessageDisableFacilityReply();
		}
		catch (SQLException e) {};
		return null;
	}

}
