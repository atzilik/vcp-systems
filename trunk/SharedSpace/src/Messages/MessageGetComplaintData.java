package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import DataObjects.FullComplaint;
import DataObjects.Reservation;

public class MessageGetComplaintData extends Message {
	

	private Connection sqlConn;
	private ArrayList<FullComplaint> complaintsArray = new ArrayList<>();
	private Date from;
	private Date to;
	private int parkingLotID;
	
	public MessageGetComplaintData(Date from, Date to, int parkingLotID) {
		this.from = from;
		this.to = to;
		this.parkingLotID = parkingLotID;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM complaints");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				if((rs.getDate(4).after(from) && rs.getDate(4).before(to)) || (rs.getDate(4).compareTo(from)) == 0 || (rs.getDate(4).compareTo(to)) == 0 )
					if(rs.getInt(9) == parkingLotID)
						complaintsArray.add(new FullComplaint(rs.getInt(1),rs.getInt(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8))); 
			
			return new MessageGetCompaintDataReply(complaintsArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}

	}

}
