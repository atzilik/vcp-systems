package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.Reservation;
import DataObjects.Worker;

public class MessageGetReservationData extends Message {
	
	private Connection sqlConn;
	private ArrayList<Reservation> reservationsArray = new ArrayList<>();


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM reservations");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				reservationsArray.add(new Reservation(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4), java.sql.Date.valueOf(rs.getString(5)),Time.valueOf(rs.getString(6)),java.sql.Date.valueOf(rs.getString(7)),Time.valueOf(rs.getString(8)))); 
			
			return new MessageGetReservationDataReply(reservationsArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}

	}
}
