package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageCancelReservation extends Message {

	private String reservationNum;
	private String customerId;

	public MessageCancelReservation(String reservationNum, String customerId) {
		super();
		this.reservationNum = reservationNum;
		this.customerId = customerId;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE reservationId=? and customerId=?");
			ps.setString(1, reservationNum);
			ps.setString(2, customerId);
			ResultSet rs = ps.executeQuery();

			if (rs.isBeforeFirst() == false)
			{
				ps.close();
				return new MessageCancelReservationReply(reservationNum, false);
			}
			else
			{
				rs.next();
				ps = con.prepareStatement("DELETE FROM reservations WHERE reservationId=?");
				ps.setString(1, rs.getString(1));
				ps.executeUpdate();
				ps.close();
				return new MessageCancelReservationReply(reservationNum, true);
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
