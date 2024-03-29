package Messages;
/**
 * This message prepares the reservation data for showing them to the CEO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.DateConvert;
import DataObjects.Reservation;
import DataObjects.Worker;

public class MessageGetReservationData extends Message {
	
	private Connection sqlConn;
	private ArrayList<Reservation> reservationsArray = new ArrayList<>();


	@Override
	/**
	 * get the reservation date for generation the report
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM reservations");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
			//	System.out.println(rs.getDate(5) + "" + rs.getTime(6) + "" + rs.getDate(7) + "" + rs.getTime(8));
				reservationsArray.add(new Reservation(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), java.sql.Date.valueOf(rs.getString(5)),rs.getTime(6),java.sql.Date.valueOf(rs.getString(7)),rs.getTime(8), rs.getBoolean(9), rs.getDouble(10), rs.getBoolean(11), java.sql.Date.valueOf(rs.getString(12))));
			}
			
			return new MessageGetReservationDataReply(reservationsArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}

	}
}
