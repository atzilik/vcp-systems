package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Reservation;

public class MessageInsertPc extends Message{
	Reservation res;
	
	public MessageInsertPc(Reservation res){
		this.res = res;
		
	}
	

	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO parking_control (customerID,carNum,parkingLotID,cinDate,cinHour,cotDate,cotHour) VALUES(?,?,?,?,?,?,?);");
			ps.setString(1,res.getCid());
			ps.setInt(2,res.getCarId());
			ps.setString(3,res.getPl());
			ps.setDate(4,res.getEstCinDate());
			ps.setTime(5,res.getEstCinHour());
			ps.setDate(6,res.getEstCoutDate());
			ps.setTime(7,res.getEstCoutHour());
	
			ps.executeUpdate();
			ps.close();
			
			return new MessageInsertPcReply(res);
			
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
}
