package Messages;

import java.io.Serializable;
import java.sql.Connection;

import DataAccessObjects.SQLConnection;

public abstract class Message implements Serializable {
	

	protected String MessageType;
	protected Connection con;
	protected static SQLConnection sqlConnection = SQLConnection.SQLConnectionSingleton();

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
	}

	abstract public Message doAction();
		
}
