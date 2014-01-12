package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import DataObjects.DateConvert;

public class MessageUpdateStatistics extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			updateStatistics(countReservationsUsed(),countReservationsCanceled(),countCurrentAmountOfMembers(),countMembersWithManyCars(),countParkingDelays());

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

	public int countRows(ResultSet rs) throws SQLException
	{
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		return count;
	}

	public int countReservationsUsed() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE estCinDate=? and used=1;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		int numOfReservationsMade = countRows(rs);
		ps.close();
		return numOfReservationsMade;
	}

	public int countReservationsCanceled() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT COUNT(DISTINCT date) FROM cancel_reservation HAVING ?;;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}
		ps.close();
		return 0;


	}

	public int countCurrentAmountOfMembers() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT COUNT(DISTINCT memberID) FROM members;");
		ResultSet rs = ps.executeQuery();
		if (rs.next())
		{
			return rs.getInt(1);
		}
		ps.close();
		return 0;
	}

	public int countMembersWithManyCars() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT memberID, COUNT(*) FROM members GROUP BY memberID HAVING COUNT(*) > 1;");
		ResultSet rs = ps.executeQuery();
		int numOfMembersWithManyCars = countRows(rs);
		ps.close();
		return numOfMembersWithManyCars;
	}

	public int countParkingDelays() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control WHERE cinDate=? and late=1;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		int numOfParkingDelays = countRows(rs);
		ps.close();
		return numOfParkingDelays;
	}

	public void updateStatistics(int ReservationsMade, int ReservationsCanceled, int amountOfMembers, int membersWithManyCars, int parkingDelays) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO daily_statistic_data (date,reservations_used,reservations_canceled,members_amount,members_with_cars,parking_delays) VALUES (?,?,?,?,?,?);");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ps.setInt(2, ReservationsMade);
		ps.setInt(3, ReservationsCanceled);
		ps.setInt(4, amountOfMembers);
		ps.setInt(5, membersWithManyCars);
		ps.setInt(6, parkingDelays);
		
		ps.executeUpdate();
		ps.close();
	}
}
