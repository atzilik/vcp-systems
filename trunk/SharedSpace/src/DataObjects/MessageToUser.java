package DataObjects;

import java.io.Serializable;

public class MessageToUser implements Serializable {
	
	private String from;
	private String to;
	private String Msg;
	
	public MessageToUser (String from, String to, String msg) {
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
