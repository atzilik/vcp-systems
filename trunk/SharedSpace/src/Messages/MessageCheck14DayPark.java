package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import DataObjects.DateConvert;

public class MessageCheck14DayPark extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			//check cars that didn't do check out yet
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where realCotDate is NULL and realCotHour is Null;");
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() == false)
				return new MessageEmptyReply();
			while(rs.next())
			{
				//check if they are members
				PreparedStatement ps1 = con.prepareStatement("SELECT * FROM members where memberID=?;");
				ps1.setString(1, rs.getString(1));
				ResultSet rs1 = ps1.executeQuery();
				if (rs1.next())
				{
					//check if they are full members
					if (rs1.getString(10).equals("3"))
					{
						//check if 14 days passed since check in
						Date CheckInDate = DateConvert.addDays(rs.getDate(4), 14);
						Time CheckInTime = rs.getTime(5);
						if (DateConvert.equalsDate(DateConvert.getCurrentSqlDate(), CheckInDate))
						{
							//check if the check in time is after 14 days
							if (DateConvert.compareTime(DateConvert.getCurrentSqlTime(), CheckInTime) == 1 || DateConvert.compareTime(DateConvert.getCurrentSqlTime(), CheckInTime) == 0)
							{
								//check out car
								PreparedStatement ps2 = con.prepareStatement("UPDATE parking_control SET realCotDate=?,realCotHour=? where customerId=? and carNum=? and parkingLotID=? and realCotDate is NULL;");
								ps2.setDate(1, DateConvert.getCurrentSqlDate());
								ps2.setTime(2, DateConvert.getCurrentSqlTime());
								ps2.setString(3, rs.getString(1));
								ps2.setString(4, rs.getString(2));
								ps2.setString(5, rs.getString(3));
								ps2.executeUpdate();
								ps2.close();
								
								ps2 = con.prepareStatement("SELECT * FROM parkinglot_map where carNum=?;");
								ps2.setString(1, rs.getString(2));
								ResultSet rs2 = ps.executeQuery();
								rs2.next();
								
								ps.close();
								return new MessageEmptyReply();
							}
						}
					}
				}
				
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
