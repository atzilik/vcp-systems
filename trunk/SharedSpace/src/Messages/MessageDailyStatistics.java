package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import DataObjects.DateConvert;

/**
 * builds the daily statistics and update DB.
 * @author Gal
 *
 */
public class MessageDailyStatistics extends Message {
	private Map<String, Integer> map;
	
	public MessageDailyStatistics(Map<String, Integer> map){
		this.map = map;
	}
	
	/**
	 *  at the end of each day, gathering all information from DB and insert it to the DB.
	 */
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			int[] reservationUsed = new int[map.size()];
			int[] reservationCanceled = new int[map.size()];
			int[][] amountOfMembers = new int[map.size()][2];
			int[][] membersWithManyCars = new int[map.size()][2];
			int[] parkingDelays = new int[map.size()];
			int[] disabledPS = new int[map.size()];
			int j = 0;
			Set keys = map.keySet();
			//building statistics for each parking lot
			for (Iterator i = keys.iterator(); i.hasNext();)
			{
				String parkinglot = (String)i.next();
				reservationUsed[j] = countReservationsUsed(map.get(parkinglot));
				reservationCanceled[j] = countReservationsCanceled(map.get(parkinglot));;
				amountOfMembers[j] = countCurrentAmountOfMembers(map.get(parkinglot));
				membersWithManyCars[j] = countMembersWithManyCars(map.get(parkinglot));
				parkingDelays[j] = countParkingDelays(map.get(parkinglot));
				disabledPS[j] = countDisabledParkingSpace(map.get(parkinglot));
				updateStatistics(map.get(parkinglot),reservationUsed[j],reservationCanceled[j],amountOfMembers[j],membersWithManyCars[j],parkingDelays[j],disabledPS[j]);
				j++;
			}
			

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
/**
 *  count the rows of a given ResultSet
 * @param rs result set from a query
 * @return number of rows after executing the query
 * @throws SQLException
 */
	public int countRows(ResultSet rs) throws SQLException
	{
		int count = 0;
		while(rs.next())
		{
			count++;
		}
		return count;
	}

	/**
	 * count the reservations that been used today
	 * @param parkingLotdID
	 * @return number of reservation been used today
	 * @throws SQLException
	 */
	public int countReservationsUsed(int parkingLotdID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE parkingLotId=? and estCinDate=? and used=1;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setInt(1, parkingLotdID);
		ps.setDate(2, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		int numOfReservationsMade = countRows(rs);
		ps.close();
		return numOfReservationsMade;
	}

	/**
	 * count the reservations that been canceled today
	 * @param parkingLotID
	 * @return number of reservation been canceledtoday
	 * @throws SQLException
	 */
	public int countReservationsCanceled(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM cancel_reservation WHERE parkingLotID=? and date=?;;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setInt(1, parkingLotID);
		ps.setDate(2, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		int numOfReservationsCanceled = countRows(rs);
		ps.close();
		return numOfReservationsCanceled;


	}

	/**
	 * count the amount of members that are active today
	 * @param parkingLotID
	 * @return amount of members that are active today
	 * @throws SQLException
	 */
	public int[] countCurrentAmountOfMembers(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM members WHERE parkingLotId=?;");
		ps.setInt(1, parkingLotID);
		ResultSet rs = ps.executeQuery();
		int[] numOfMembers = new int[2];
		numOfMembers[0] = countRows(rs);
		ps.close();
		ps = con.prepareStatement("SELECT * FROM members WHERE parkingLotId is NULL;");
		rs = ps.executeQuery();
		numOfMembers[1] = countRows(rs);
		ps.close();
		return numOfMembers;
		
	}

	/**
	 * count the amount of members that have more than 1 car 
	 * @param parkingLotID
	 * @return
	 * @throws SQLException
	 */
	public int[] countMembersWithManyCars(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT memberID, COUNT(*) FROM members GROUP BY memberID,parkingLotId HAVING COUNT(*) > 1 and parkingLotID=?;");
		ps.setInt(1, parkingLotID);
		ResultSet rs = ps.executeQuery();
		int[] numOfMembersWithManyCars = new int[2];
		numOfMembersWithManyCars[0] = countRows(rs);
		ps.close();
		ps = con.prepareStatement("SELECT memberID, COUNT(*) FROM vcp.members GROUP BY memberID,parkingLotId HAVING COUNT(*) > 1 and parkingLotId is NULL;");
		rs = ps.executeQuery();
		numOfMembersWithManyCars[1] = countRows(rs);
		ps.close();
		return numOfMembersWithManyCars;
	}

	/**
	 * count the amount of parking delays in the last day
	 * @param parkingLotID
	 * @return amount of parking delays in the last day
	 * @throws SQLException
	 */
	public int countParkingDelays(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control WHERE parkingLotID=? and cinDate=? and late=1;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setInt(1, parkingLotID);
		ps.setDate(2, new Date(cal.getTimeInMillis()));
		ResultSet rs = ps.executeQuery();
		int numOfParkingDelays = countRows(rs);
		ps.close();
		return numOfParkingDelays;
	}
	
	/**
	 * count the amount of disabled parking space in the last day
	 * @param parkingLotID
	 * @return
	 * @throws SQLException
	 */
	public int countDisabledParkingSpace(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM disabled_parkingspace WHERE parkingLotID=? and enabledDate is NULL;");
		ps.setInt(1, parkingLotID);
		ResultSet rs = ps.executeQuery();
		int numOfDisabledParkingSpace = countRows(rs);
		ps.close();
		return numOfDisabledParkingSpace;
	}

	/**
	 * gather up all the data and update DB.
	 * @param parkingLotID
	 * @param ReservationsMade amount of reservations made
	 * @param ReservationsCanceled amount of reservations canceled
	 * @param amountOfMembers amount of members
	 * @param membersWithManyCars amount of members with more than 1 car
	 * @param parkingDelays amount of parking delays
	 * @param disalbed amount of disabled parking spaces
	 * @throws SQLException
	 */
	public void updateStatistics(int parkingLotID, int ReservationsMade, int ReservationsCanceled, int[] amountOfMembers, int[] membersWithManyCars, int parkingDelays, int disalbed) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO daily_statistic_data (date,parkingLotID,reservations_used,reservations_canceled,STDmembers_amount,fullMember_amount,STDmembers_with_cars,fullMember_with_cars,parking_delays, disabled) VALUES (?,?,?,?,?,?,?,?,?,?);");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -1);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ps.setInt(2, parkingLotID);
		ps.setInt(3, ReservationsMade);
		ps.setInt(4, ReservationsCanceled);
		ps.setInt(5, amountOfMembers[0]);
		ps.setInt(6, amountOfMembers[1]);
		ps.setInt(7, membersWithManyCars[0]);
		ps.setInt(8, membersWithManyCars[1]);
		ps.setInt(9, parkingDelays);
		ps.setInt(10, disalbed);
		
		ps.executeUpdate();
		ps.close();
	}
}
