package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageInsertReservation extends Message {

	private Connection sqlConn;
	private String[] details;

	
	public MessageInsertReservation(String[] det) {
		this.MessageType = "MessageInsertReservation";
		details = det;
	}
	
	@Override
	public Message doAction() {
		
		sqlConn = this.sqlConnection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO reservations (reservationId,carId,customerId,parkingLotId,estCinDate,estCinHour,estCotDate,estCotHour) VALUES(?,?,?,?,?,?,?,?);");
			for (int i = 1 ; i < details.length - 1; i++)
			{
				ps.setString(i, details[i]);
			}
			ps.executeUpdate();
			ps.close();
			
			this.setTransactionSucceeded(true);	
												
		} catch (SQLException e) {
						
			this.setTransactionSucceeded(false);	
			e.printStackTrace();
			return this;
			
		}
		return this;
	}
}
