package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * changes the Full status of a parking lot
 * @author Gal
 *
 */
public class MessageFullPLStatus extends Message {
	private int parkingLotID;
	/**
	 * true = if need to update its full.
	 * false = if need to update from full to not.
	 */
	private boolean full;
	
	public MessageFullPLStatus(int parkingLotID, boolean full){
		this.parkingLotID = parkingLotID;
		this.full = full;
	}
	
	
	/**
	 * 
	 */
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			
			PreparedStatement ps = con.prepareStatement("UPDATE parkinglots SET full=? WHERE parkingLotID=?;");
			ps.setBoolean(1, full);
			ps.setInt(2, parkingLotID);
			ps.executeUpdate();
			ps.close();
			return new MessageEmptyReply();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
