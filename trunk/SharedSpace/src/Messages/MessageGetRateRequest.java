package Messages;
/**
 * This message prepares the rates request data for showing them to the CEO
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.RateRequest;

public class MessageGetRateRequest extends Message {
	/**
	 * sql connection
	 */
	private Connection sqlConn;
	/**
	 * rates requests
	 */
	private ArrayList<RateRequest> rateArray = new ArrayList<>();
	
	public MessageGetRateRequest() {
		this.MessageType = "MessageGetRateRequest";
	}

	@Override
	/**
	 * gets the rates data for showing them to the CEO
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM ratereq");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				rateArray.add(new RateRequest(rs.getInt(1), rs.getFloat(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5), rs.getString(6))); 
			
			return new MessageGetRateRequestReply(rateArray);
			
		}catch (SQLException e) {
			return null;
		}
		
	}

}
