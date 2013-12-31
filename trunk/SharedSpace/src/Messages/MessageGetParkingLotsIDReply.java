package Messages;

import java.util.Map;

public class MessageGetParkingLotsIDReply extends Message {
	private Map<String, Integer> mp;
	
	public MessageGetParkingLotsIDReply(Map<String, Integer> mp){
		this.mp = mp;
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getMp() {
		return mp;
	}
}
