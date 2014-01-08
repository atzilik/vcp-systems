package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import DataObjects.MessageToUser;

public class MessageSendMessage extends Message {

	private Connection sqlConn;
	
	private String from;
	private String to;
	private String msg;
	
	public MessageSendMessage(MessageToUser msg) {
		this.from = msg.getFrom();
		this.to = msg.getTo();
		this.msg = msg.getMsg();
	}
	public Message doAction() {
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("INSERT INTO messages (MsgID,FromId,ToId,Message) VALUES(?,?,?,?);");
			ps.setInt(1, (100000 + new Random().nextInt(900000)));
			ps.setString(2, from);
			ps.setString(3, to);
			ps.setString(4, msg);
			ps.executeUpdate();
			
		}catch (SQLException e) {
			return null;
		}
		return null;
	}

}
