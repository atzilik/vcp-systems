package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.DateConvert;
import DataObjects.ParkingLot;

/**
 * 
 * @author omri
 *This class is responsible for report a disable parking space.
 */
public class MessageReportDisabledPSpace extends Message {
	/**
	 * details = [parkingLotID,floor,row,depth]
	 */
private String[] details; 
private boolean disabled;
	public MessageReportDisabledPSpace(String[] details,boolean disabled) {
	this.details = details;
	this.disabled=disabled;
}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try {// disable a parking space
			/**
			 * ps is the result of sql query which inserts a new disabled parking space into parkinglot_map table
			 */
			if (disabled==true){ // disable a parking space
			PreparedStatement ps = con.prepareStatement("INSERT INTO parkinglot_map (parkingLotID,floor,row,depth,reserved,disabled) VALUES(?,?,?,?,?,?);");
			ps.setString(1,details[0]);
			ps.setString(2,details[1]);
			ps.setString(3,details[2]);
			ps.setString(4,details[3]);
			ps.setBoolean(5,false);
			ps.setBoolean(6,disabled);
			
			
			ps.executeUpdate();
			ps.close();
			/**
			 * ps2 is the result of sql query which inserts the date and time stamp details of the disabled parking space
			 */
			PreparedStatement ps2= con.prepareStatement("INSERT INTO disabled_parkingspace (parkingLotID,floor,row,depth,disabledDate,disabled_time) VALUES(?,?,?,?,?,?);");
				ps2.setString(1,details[0]);
				ps2.setString(2,details[1]);
				ps2.setString(3,details[2]);
				ps2.setString(4,details[3]);
				ps2.setDate(5, DateConvert.getCurrentSqlDate());
				ps2.setTime(6, DateConvert.getCurrentSqlTime());
				ps2.executeUpdate();
				ps2.close();
				//update the parkinglot_map
			/**
			 * ps3 is the result of sql query which updates the parkinglot_map if the parkinglot is set back to enabled status. 
			 */
			}
			else // enable a parking space
			{
				/**
				 * ps3 is the result of sql query which updates the parkinglot_map if the parkinglot is re enabled. 
				 */
			PreparedStatement ps3= con.prepareStatement("UPDATE parkinglot_map SET disabled=? where parkingLotID=? and floor=? and row=? and depth=?;");
			ps3.setBoolean(1,disabled);
			ps3.setString(2,details[0]);
			ps3.setString(3,details[1]);
			ps3.setString(4,details[2]);
			ps3.setString(5,details[3]);
			ps3.executeUpdate();
			ps3.close();
			/**
			 * ps4 is the result of sql query which reset the enable time/date in the disabled_parkingspace table 
			 */
			PreparedStatement ps4= con.prepareStatement("UPDATE disabled_parkingspace SET enabledDate=?, enabled_time=? where parkingLotID=? and floor=? and row=? and depth=? and enabledDate is null and enabled_time is null;");
			ps4.setDate(1, DateConvert.getCurrentSqlDate());
			ps4.setTime(2, DateConvert.getCurrentSqlTime());
			ps4.setString(3,details[0]);
			ps4.setString(4,details[1]);
			ps4.setString(5,details[2]);
			ps4.setString(6,details[3]);
			ps4.executeUpdate();
			ps4.close();
			}
			return new MessageReportDisabledPSpaceReply(disabled);
			
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
