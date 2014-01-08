package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DataObjects.RateRequest;

public class MessageAprroveRateRequest extends Message {
	
	private Connection sqlConn;
	
	RateRequest req;
	boolean isAprroved;
	
	public MessageAprroveRateRequest(RateRequest req, boolean isAprroved) {
		this.req = req;
		this.isAprroved = isAprroved;
	}

	
	@Override
	public Message doAction() {
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("DELETE FROM ratereq WHERE `ParkingLotId`=?;");
			ps.setInt(1, req.getParkingLotId());
			ps.executeUpdate();
			
			if(isAprroved) // להוסיף שליחת הודעה על קבלה
			{
				ps = sqlConn.prepareStatement("UPDATE parkinglots SET OccasionalRate=?,ReservedRate=?,StandardRate=?,FullRate=? WHERE parkingLotID=?;");
				ps.setFloat(1, req.getOccasional());
				ps.setFloat(2, req.getReserved());
				ps.setFloat(3, req.getStandard());
				ps.setFloat(4, req.getFull());
				ps.setInt(5, req.getParkingLotId());
				ps.executeUpdate();
			}

			return new MessageAprroveRateRequestReplay(true);
			
		}catch (SQLException e) {
			return new MessageAprroveRateRequestReplay(false);
		}
	}

}
