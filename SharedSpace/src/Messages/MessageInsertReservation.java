package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MessageInsertReservation extends Message {
	private String[] details;

	
	public MessageInsertReservation(String[] det) {
//		this.MessageType = "MessageInsertReservation";
		details = det;
	}
	
	@Override
	public Message doAction() {
		
		con = this.sqlConnection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO reservations (reservationId,carId,customerId,parkingLotId,estCinDate,estCinHour,estCotDate,estCotHour) VALUES(?,?,?,?,?,?,?,?);");
			for (int i = 0 ; i < details.length; i++)
			{
				ps.setString(i + 1, details[i]);
			}
			ps.executeUpdate();
			ps.close();
			
			this.setTransactionSucceeded(true);	
			return new MessageInsertReservationReply(details[0]);
												
		} catch (SQLException e) {
						
			this.setTransactionSucceeded(false);
			if (e.getMessage().contains("Duplicate entry"))
			{
				details[0] = Integer.toString(100000 + new Random().nextInt(900000));
				new MessageInsertReservation(details).doAction();
			}		
		}
		return null;
	}
}
