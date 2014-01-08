package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.ParkingLot;

public class MessageReportDisabledPSpace extends Message {
private String[] details; 

	public MessageReportDisabledPSpace(String[] details) {
	this.details = details;
}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO parkinglot_map (parkingLotID,floor,row,depth,reserved,disabled) VALUES(?,?,?,?,?,?);");
			ps.setString(1,details[0]);
			ps.setString(2,details[1]);
			ps.setString(3,details[2]);
			ps.setString(4,details[3]);
			ps.setBoolean(5,false);
			ps.setBoolean(6,true);
			
			
			ps.executeUpdate();
			ps.close();
			
			return new MessageReportDisabledPSpaceReply();
			
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
