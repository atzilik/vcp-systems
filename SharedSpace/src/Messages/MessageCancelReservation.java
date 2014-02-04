package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import DataObjects.DateConvert;

/**
 * message that handle a cancel reservation request
 * @author Gal
 *
 */
public class MessageCancelReservation extends Message {

	private String reservationNum;
	private String customerId;
	private String carNumber;

	public MessageCancelReservation(String reservationNum, String customerId, String carNumber) {
		super();
		this.reservationNum = reservationNum;
		this.customerId = customerId;
		this.carNumber = carNumber;
	}


	/**
	 * get a cancel reservation request, check if a fine require and update db
	 */
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
				return new MessageCancelReservationReply(reservationNum, false, 0);
			}
			else
			{
				rs.next();
				//check if reservation already used
				if (rs.getBoolean(11))
				{
					return new MessageCancelReservationReply(reservationNum, false, 0);
				}
				//if not then insert to cancel_reservation table and delete it from reservation table
				double refund = calculateRefund(rs.getDate(5), rs.getTime(6), rs.getDouble(10));
				PreparedStatement ps1 = con.prepareStatement("INSERT INTO cancel_reservation (reservationId,date,customerID,carNumner,refund) VALUES (?,?,?,?,?);");
				ps1.setString(1, rs.getString(1));
				ps.setDate(2, DateConvert.getCurrentSqlDate());
				ps.setString(3, customerId);
				ps.setString(4, carNumber);
				ps1.setDouble(5, refund);
				ps1.executeUpdate();
				ps1.close();
				
				ps = con.prepareStatement("DELETE FROM reservations WHERE reservationId=?");
				ps.setString(1, rs.getString(1));
				ps.executeUpdate();
				ps.close();
				return new MessageCancelReservationReply(reservationNum, true, refund);
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public double calculateRefund(Date checkInDate, Time CheckInTime, double rate){
		java.util.Date currentTime = DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime());
		java.util.Date checkInTime = DateConvert.buildFullDate(checkInDate, CheckInTime);
		long timeDifference = DateConvert.timeDifference(currentTime, checkInTime);
		//check if it is more than 3 hours before cancel, customer get full refund
		if (timeDifference > TimeUnit.HOURS.toMinutes(3))
		{
			return rate;
		}
		//if it is between 1 and 3 hours customer get 50% refund
		if (timeDifference < TimeUnit.HOURS.toMinutes(3) && timeDifference > TimeUnit.HOURS.toMinutes(1))
		{
			return rate * 0.5;
		}
		return 0;
	}

}
