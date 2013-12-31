package Messages;

import java.util.List;

public class MessageGetCarsIDReply extends Message {
	
	private List<String> ls;
	
	public MessageGetCarsIDReply(List<String> ls){
		this.ls = ls;
	}

	
	public List<String> getLs() {
		return ls;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
