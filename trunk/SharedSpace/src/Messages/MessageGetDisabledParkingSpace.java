package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.DisabledParkingSpace;
import DataObjects.Reservation;

public class MessageGetDisabledParkingSpace extends Message {
	
	private Connection sqlConn;
	private ArrayList<DisabledParkingSpace> disabledArray = new ArrayList<>();

	@Override
	public Message doAction() {
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM disabled_parkingspace");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				disabledArray.add(new DisabledParkingSpace(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), java.sql.Date.valueOf(rs.getString(5)),rs.getTime(6),java.sql.Date.valueOf(rs.getString(7)),rs.getTime(8)));
			
			return new MessageGetDisabledParkingSpaceReply(disabledArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}

	}
}
