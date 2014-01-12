package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.Customer;
import DataObjects.FullComplaint;
import DataObjects.Reservation;

public class MessageCheckReservation extends Message{
	private ArrayList<Reservation> reservationArray = new ArrayList<>();
	private String id;
	
	public MessageCheckReservation (String id) {
		this.id = id;
	}

	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findRes(); // check id 
			if (rs == null)
				return new MessageCheckReservationReply();  // no such record on reservation
			else
			{
				do
				{
					reservationArray.add(new Reservation(rs.getString(1),rs.getInt(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getTime(6), rs.getDate(7), rs.getTime(8))); 
				}while(rs.next());
				return new MessageCheckReservationReply(reservationArray);
			}
				
			}catch (SQLException e) {e.printStackTrace();}
			return null;
	}
	
		
	public ResultSet findRes() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations where customerId=?;");
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
			
		if (rs.next())
		{
			return rs;
		}	
		return null;
	}

}
