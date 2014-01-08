package Messages;

import java.util.ArrayList;

import DataObjects.MessageToUser;
import DataObjects.RateRequest;

public class MessageGetMessageReplay extends Message {

	private ArrayList<MessageToUser> msgArr;
	
	public MessageGetMessageReplay(ArrayList<MessageToUser> msgArr) {
		this.setMsgArr(msgArr);
	}
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<MessageToUser> getMsgArr() {
		return msgArr;
	}
	public void setMsgArr(ArrayList<MessageToUser> msgArr) {
		this.msgArr = msgArr;
	}
}

