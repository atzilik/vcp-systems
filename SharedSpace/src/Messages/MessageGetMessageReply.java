package Messages;

import java.util.ArrayList;

import DataObjects.DataObjectMessageToUser;
import DataObjects.RateRequest;

public class MessageGetMessageReply extends Message {

	private ArrayList<DataObjectMessageToUser> msgArr;
	
	public MessageGetMessageReply(ArrayList<DataObjectMessageToUser> msgArr) {
		this.setMsgArr(msgArr);
	}
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<DataObjectMessageToUser> getMsgArr() {
		return msgArr;
	}
	public void setMsgArr(ArrayList<DataObjectMessageToUser> msgArr) {
		this.msgArr = msgArr;
	}
}

