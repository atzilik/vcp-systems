package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * This message is responsible of disabling the facility
 * @author Alon
 *
 */
public class MessageDisableFacility extends Message {
	private int parkingLotID;

	public MessageDisableFacility(int parkingLotID) {
		this.parkingLotID = parkingLotID;
	}

	@Override
	/**
	 * updates the parkinglots fields table in db  
	 */
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
