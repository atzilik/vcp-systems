package Messages;

import java.io.Serializable;
import java.sql.Connection;

import DataAccessObjects.SQLConnection;

public abstract class Message implements Serializable {
	

	protected String MessageType;
	protected Connection con;
	protected static SQLConnection sqlConnection = SQLConnection.SQLConnectionSingleton();
	protected static int MessageBetweenUsersNUM;
	private boolean transactionSucceeded = false;

	public String getMessageType() {
		return MessageType;
	}

	public void setMessageType(String messageType) {
		MessageType = messageType;
	}
	
	public boolean isTransactionSucceeded() {
		return transactionSucceeded;
	}

	public void setTransactionSucceeded(boolean transactionSucceeded) {
		this.transactionSucceeded = transactionSucceeded;
	}

	abstract public Message doAction();
		
}
