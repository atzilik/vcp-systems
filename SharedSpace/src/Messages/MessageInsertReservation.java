package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.Random;

import DataObjects.DateConvert;

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
			//insert reservation to reservations table
			PreparedStatement ps = con.prepareStatement("INSERT INTO reservations (reservationId,carId,customerId,parkingLotId,estCinDate,estCinHour,estCotDate,estCotHour,inAdvance,estBill,reservationDate) VALUES(?,?,?,?,?,?,?,?,?,?,?);");
			for (int i = 0 ; i < details.length - 1; i++)
			{
				ps.setString(i + 1, details[i]);
			}
			if (details[8].equals("1"))
			{
				ps.setBoolean(9, true);
			}
			else
			{
				ps.setBoolean(9, false);
			}
			
			
			double bill;
			//getting the parking lot rates in order to calculate estimated bill
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM parkinglots WHERE parkingLotID=?;");
			ps1.setString(1, details[3]);
			ResultSet rs = ps1.executeQuery();
			if (rs.next())
			{
				double occRate = rs.getDouble(7);
				double resRate = rs.getDouble(8);
				ps1.close();
				//getting the dates in order to calculate bill
				Date cinDate = DateConvert.stringToSQLDate(details[4]);
				Time cinTime = DateConvert.stringToSQLTime(details[5]);
				Date cotDate = DateConvert.stringToSQLDate(details[6]);
				Time cotTime = DateConvert.stringToSQLTime(details[7]);
				// if inAdvance = 1 then its reservation from advance
				if (details[8].equals("1"))
				{
					//calculate bill
					//get time difference in minutes and multiple it by rate/60
					bill = DateConvert.timeDifference(DateConvert.buildFullDate(cotDate,cotTime),DateConvert.buildFullDate(cinDate,cinTime)) * (resRate / 60);			
				}
				else
				{//wrong data if occasional customer put an early hour then the current hour
					bill = DateConvert.timeDifference(DateConvert.buildFullDate(cotDate,cotTime),DateConvert.buildFullDate(cinDate,cinTime)) * (occRate / 60);
				}
				ps.setDouble(10, bill);
				ps.setDate(11, DateConvert.getCurrentSqlDate());
				ps.executeUpdate();
			}
			else
			{
				ps1.close();
				return new MessageInsertReservationReply();
			}
			this.setTransactionSucceeded(true);	
			return new MessageInsertReservationReply(details[0], bill);

		} catch (SQLException e) {

			e.printStackTrace();
			this.setTransactionSucceeded(false);	
		}
		return null;
	}
}
