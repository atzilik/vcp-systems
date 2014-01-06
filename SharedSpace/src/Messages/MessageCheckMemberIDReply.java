package Messages;

public class MessageCheckMemberIDReply extends Message {
	private String memberID;
	
	

	public MessageCheckMemberIDReply(String memberIDExist) {
		this.memberID = memberIDExist;
	}



	public String getMemberID() {
		return memberID;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
