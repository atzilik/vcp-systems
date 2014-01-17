package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

import DataObjects.DateConvert;

/**
 * checks if a full member parked for more than 14 days straight
 * @author Gal
 *
 */
public class MessageCheck14DayPark extends Message {


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			//check cars that didn't do check out yet
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where realCotDate is NULL and realCotHour is Null;");
			ResultSet rs = ps.executeQuery();
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
						if (TimeUnit.MINUTES.toDays(DateConvert.timeDifference(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime(),rs.getDate(4), rs.getTime(5))) >= 14)
						{
							//fine the customer
							PreparedStatement ps2 = con.prepareStatement("INSERT INTO customer_bill (customerID,carID,date,time,sum) VALUES (?,?,?,?,?);");
							ps2.setString(1, rs.getString(1));
							ps2.setString(2, rs.getString(2));
							ps2.setDate(3, DateConvert.getCurrentSqlDate());
							ps2.setTime(4, DateConvert.getCurrentSqlTime());
							ps2.setDouble(5, 500);
							ps2.executeUpdate();
							ps2.close();
						}
					}
				}
				ps1.close();
			}
			ps.close();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
