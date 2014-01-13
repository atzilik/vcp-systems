package DataObjects;

import java.io.Serializable;
/**
 * This class is a message structure which provides the ability of sending messages and notifications between users
 * @author Alon
 *
 */
public class DataObjectMessageToUser implements Serializable {
	/**
	 * Where the message is from
	 */
	private String from;
	/**
	 * Where the message is sent to
	 */
	private String to;
	/**
	 * the message itself
	 */
	private String Msg;
	/**
	 * loads the message details
	 * @param from Where the message is from
	 * @param to Where the message is sent to
	 * @param msg the message itself
	 */
	public DataObjectMessageToUser (String from, String to, String msg) {
		this.from = from;
		this.to = to;
		this.Msg = msg;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMsg() {
		return Msg;
	}
	public void setMsg(String msg) {
		Msg = msg;
	}
	
}
