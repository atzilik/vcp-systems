package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import DataObjects.DateConvert;

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
				//check if reservation already used
				if (rs.getBoolean(11))
				{
					return new MessageCancelReservationReply(reservationNum, false);
				}
				//if not then insert to cancel_reservation table and delete it from reservation table
				double refund = 0;
				//check if it is more than 3 hours before cancel, customer get full refund
				if (DateConvert.timeDifference(DateConvert.buildFullDate(rs.getDate(5), rs.getTime(6)),DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime())) > TimeUnit.HOURS.toMinutes(3))
				{
					refund = rs.getDouble(10);
				}
				//if it is between 1 and 3 hours customer get 50% refund
				else if (DateConvert.timeDifference(DateConvert.buildFullDate(rs.getDate(5), rs.getTime(6)),DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime())) < TimeUnit.HOURS.toMinutes(3) && DateConvert.timeDifference(DateConvert.buildFullDate(rs.getDate(5), rs.getTime(6)),DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime())) > TimeUnit.HOURS.toMinutes(1))
				{
					refund = rs.getDouble(10) * 0.5;
				}
				else
				{
					refund = 0;
				}
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO cancel_reservation (reservationId,refund) VALUES (?,?);");
				ps1.setString(1, rs.getString(1));
				ps1.setDouble(2, refund);
				ps1.executeUpdate();
				ps1.close();
				
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
