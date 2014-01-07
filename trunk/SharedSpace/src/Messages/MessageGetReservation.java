package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.Map;

import DataObjects.FullMember;
import DataObjects.Reservation;
import DataObjects.STDCustomer;
import DataObjects.STDMember;

public class MessageGetReservation extends Message{
	private String carNum;
	private String id;
	Reservation res;
	String pl;
	Date todayDate = new java.sql.Date(new java.util.Date().getTime()); 
	Time currTime = new java.sql.Time(new java.util.Date().getTime());
	
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
				if (rs == null)
					return new MessageGetReservationReply();
				else
				{
					do
					{
					res = new Reservation(rs.getString(1),rs.getInt(2),rs.getString(3),
							        	  rs.getString(4),rs.getDate(5),rs.getTime(6),rs.getDate(7),rs.getTime(8));
					if (pl.equals(res.getPl()) && todayDate.equals(res.getEstCinDate()))
						// the chack in is in the same pl that reserved and the date of today is the same as the res
						
						return new MessageGetReservationReply(res);
					}
					while (rs.next());
					return new MessageGetReservationReply();
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
			
			
	
}
