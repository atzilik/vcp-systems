package Messages;

import java.io.Serializable;
import java.sql.Connection;

import DataAccessObjects.SQLConnection;
/**
 * messages between server and client
 * @author Gal
 *
 */
public abstract class Message implements Serializable {
	
	/**
	 * message type
	 */
	protected String MessageType;
	/**
	 * holds the SQL connection
	 */
	protected Connection con;
	/**
	 * make the SQL connection
	 */
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
