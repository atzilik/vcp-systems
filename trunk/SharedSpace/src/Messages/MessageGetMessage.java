package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.MessageToUser;
import DataObjects.RateRequest;

public class MessageGetMessage extends Message {
	
	private String id;
	private ArrayList<MessageToUser> msgArray = new ArrayList<>();
	private ArrayList<Integer> msgToDelete = new ArrayList<>();
	public MessageGetMessage(String id) {
		this.id=id;
	}

	private Connection sqlConn;
	@Override
	public Message doAction() {
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM messages");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				if(rs.getString(3).equals(id))
				{
					msgArray.add(new MessageToUser(rs.getString(2), rs.getString(3), rs.getString(4))); 
					msgToDelete.add(rs.getInt(1));
				}
			}
			
			for(Integer toDel:msgToDelete)
			{
				ps = sqlConn.prepareStatement("DELETE FROM messages WHERE `MsgID`=?;");
				ps.setInt(1,toDel);
				ps.executeUpdate();
			}
			
			return new MessageGetMessageReplay(msgArray);
			
		}catch (SQLException e) {
			return null;
		}
	}

}
