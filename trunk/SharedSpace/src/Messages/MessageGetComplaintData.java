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
/**
 * This message is responsible of collecting the complaints from the DB for generating the complaints report
 * @author Alon
 *
 */
public class MessageGetComplaintData extends Message {
	
	/**
	 * sql connection
	 */
	private Connection sqlConn;
	/**
	 * complaints array
	 */
	private ArrayList<FullComplaint> complaintsArray = new ArrayList<>();
	/**
	 * the start date for the report
	 */
	private Date from;
	/**
	 * the end date for the report
	 */
	private Date to;
	/**
	 * parking lot id
	 */
	private int parkingLotID;
	/**
	 * loads the details for the report
	 * @param from start date
	 * @param to`end date
	 * @param parkingLotID
	 */
	
	public MessageGetComplaintData(Date from, Date to, int parkingLotID) {
		this.from = from;
		this.to = to;
		this.parkingLotID = parkingLotID;
	}

	@Override
	/**
	 * gets the relevant complaints data according to the date range
	 */
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
