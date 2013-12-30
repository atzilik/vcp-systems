package Messages;

import java.sql.*;


public class MessageChangeParkingFees extends Message {

	private Connection sqlConn;
	private MessageChangeParkingFeesReply msgCPFreply = new MessageChangeParkingFeesReply();
	
	public MessageChangeParkingFees() {
		
		this.MessageType = "MessageChangeParkingFees";
	}
	
	@Override
	public Message doAction() {
		
		sqlConn = this.sqlConnection.getConnection();
		
		try {
			PreparedStatement chgParkingFees = sqlConn.prepareStatement("UPDATE TABLE...BLABLABLA");
			chgParkingFees.executeUpdate();
						
			msgCPFreply.setTransactionSucceeded(true);			
					
			
		} catch (SQLException e) {
						
			msgCPFreply.setTransactionSucceeded(false);
			e.printStackTrace();
			return msgCPFreply;
			
		}
		return msgCPFreply;
		
		
		
	}

}
