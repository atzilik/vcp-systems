package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Calendar;
import java.util.Map;
import java.sql.Date;
import DataObjects.DateConvert;
import DataObjects.FullMember;
import DataObjects.Reservation;
import DataObjects.STDCustomer;
import DataObjects.STDMember;

/**
 * 
 * @author Boaz
 *This class is responsible for getting the reservation.
 */
public class MessageGetReservation extends Message{
	private String carNum;
	private String id;
	Reservation res;
	String pl;
	java.util.Date todayDate = DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime());


/**
 * 
 * @param id id of the customer
 * @param carNum car number of the customer
 * @param pl string of the parkinglot number
 */
	public MessageGetReservation (String id, String carNum, String pl) {
		this.carNum = carNum;
		this.id = id;
		this.pl = pl;
	}
	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findReservation();
			if (rs == null)  // no reservation record
				return new MessageGetReservationReply(2);  // customer arrived early
			else
			{
				do
				{
					res = new Reservation(rs.getString(1),rs.getInt(2),rs.getString(3),
							rs.getString(4),rs.getDate(5),rs.getTime(6),rs.getDate(7),rs.getTime(8),rs.getBoolean(9),rs.getDouble(10),rs.getBoolean(11),rs.getDate(12));
					java.util.Date resCheckInDate = DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour());
					//checking if parking lot matching and customer didn't arrive early
					if (DateConvert.timeDifference(todayDate,resCheckInDate) >= 0)
					{
						//check if customer is late, 2 minutes and below delay arriving is acceptable
						if (DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) == 0 || DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) <= 2)
						{
							updateReservationUsed();
							return new MessageGetReservationReply(res, false);  // late or not
						}
						else
						{
							updateReservationUsed();
							return new MessageGetReservationReply(res, true);  // late or not
						}
					}
					//customer arrive too early
					else if(DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) < 0)
					{
						return new MessageGetReservationReply(1);  // early
					}
				}
				while (rs.next());
				return new MessageGetReservationReply(2);  // no reservation
			}

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	} // doAction

	/**
	 * 
	 * @return the result reservation of the customer that didn't already parked
	 * @throws SQLException
	 */
	public ResultSet findReservation() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations where customerId=? and carId=? and parkingLotId=? and used=0;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ps.setString(3, pl);
		ResultSet rs = ps.executeQuery();

		if (rs.next())
		{
			return rs;
		}	
		return null;
	}
	
	/**
	 * this method is updating the reservation table if customer did check in
	 * @throws SQLException
	 */
	public void updateReservationUsed() throws SQLException{
		PreparedStatement ps = con.prepareStatement("UPDATE reservations SET used=? WHERE reservationId=?;");
		ps.setBoolean(1, true);
		ps.setString(2, res.getRid());
		ps.executeUpdate();
		ps.close();
	}
}
