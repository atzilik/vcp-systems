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

public class MessageGetReservation extends Message{
	private static final Calendar calender = null;
	private String carNum;
	private String id;
	Reservation res;
	String pl;
	java.util.Date todayDate = DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime());
//	int day = Calendar.getInstance(Cal.)
	int day;



	public MessageGetReservation (String id, String carNum, String pl) {
		this.carNum = carNum;
		this.id = id;
		this.pl = pl;
		Calendar cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_WEEK);
		
	}
	@Override
	public Message doAction() {
		System.out.print(day);
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findReservation();
			if (rs == null)
				return new MessageGetReservationReply(2);
			else
			{
				do
				{
					res = new Reservation(rs.getString(1),rs.getInt(2),rs.getString(3),
							rs.getString(4),rs.getDate(5),rs.getTime(6),rs.getDate(7),rs.getTime(8));
//					if (pl.equals(res.getPl()) && DateConvert.equalsDate(todayDate, res.getEstCinDate()))
//						// the chack in is in the same pl that reserved and the date of today is the same as the res
//						if (DateConvert.compareTime(res.getEstCinHour(),currTime)==0||DateConvert.compareTime(res.getEstCinHour(),currTime)==-1)
//							return new MessageGetReservationReply(res);
//						else
//							return new MessageGetReservationReply(1);
					java.util.Date resCheckInDate = DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour());
					//checking if parking lot matching and customer didn't arrive early
					if (pl.equals(res.getPl()) && DateConvert.timeDifference(todayDate,resCheckInDate) >= 0)
					{
						//check if customer is late, 2 minutes and below delay arriving is acceptable
						if (DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) == 0 || DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) <= 2)
						{
							updateReservationUsed();
							return new MessageGetReservationReply(res, false);
						}
						else
						{
							updateReservationUsed();
							return new MessageGetReservationReply(res, true);
						}
					}
					//customer arrive too early
					else if(DateConvert.timeDifference(todayDate,DateConvert.buildFullDate(res.getEstCinDate(), res.getEstCinHour())) < 0)
					{
						return new MessageGetReservationReply(1);
					}
				}
				while (rs.next());
				return new MessageGetReservationReply(2);
			}

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	} // doAction

	public ResultSet findReservation() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations where customerId=? and carId=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ResultSet rs = ps.executeQuery();

		if (rs.next())
		{
			return rs;
		}	
		return null;
	}
	
	public void updateReservationUsed() throws SQLException{
		PreparedStatement ps = con.prepareStatement("UPDATE reservations SET used=? WHERE reservationId=?;");
		ps.setBoolean(1, true);
		ps.setString(2, res.getRid());
		ps.executeUpdate();
		ps.close();
	}
}
