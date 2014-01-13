package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DataObjects.RateRequest;
/**
 * This message is responsible of approving rate requests of the parking lot managers
 * @author Alon
 *
 */
public class MessageAprroveRateRequest extends Message {
	/**
	 * sql connection
	 */
	private Connection sqlConn;
	/**
	 * rate request details
	 */
	RateRequest req;
	/**
	 * rate request approved or not
	 */
	boolean isAprroved;
	/**
	 * loads the needed details
	 * @param req rate request details
	 * @param isAprroved approved (yes/no)
	 */
	public MessageAprroveRateRequest(RateRequest req, boolean isAprroved) {
		this.req = req;
		this.isAprroved = isAprroved;
	}

	
	@Override
	/**
	 * delete the row in the ratereq table in DB and update the relevant fields with the new rates in the parking lots table 
	 */
	public Message doAction() {
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("DELETE FROM ratereq WHERE `ParkingLotId`=?;");
			ps.setInt(1, req.getParkingLotId());
			ps.executeUpdate();
			
			if(isAprroved) 
			{
				ps = sqlConn.prepareStatement("UPDATE parkinglots SET OccasionalRate=?,ReservedRate=?,StandardRate=?,FullRate=? WHERE parkingLotID=?;");
				ps.setFloat(1, req.getOccasional());
				ps.setFloat(2, req.getReserved());
				ps.setFloat(3, req.getStandard());
				ps.setFloat(4, req.getFull());
				ps.setInt(5, req.getParkingLotId());
				ps.executeUpdate();
			}

			return new MessageAprroveRateRequestReply(true);
			
		}catch (SQLException e) {
			return new MessageAprroveRateRequestReply(false);
		}
	}

}
