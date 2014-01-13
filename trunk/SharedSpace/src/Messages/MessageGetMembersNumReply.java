package Messages;

public class MessageGetMembersNumReply extends Message {
	
	private int numOfMembersWithManyCars;
	private int numOfMembers;
	
	public MessageGetMembersNumReply(int numOfMembersWithManyCars, int numOfMembers) {
		this.numOfMembersWithManyCars = numOfMembersWithManyCars;
		this.numOfMembers = numOfMembers;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumOfMembersWithManyCars() {
		return numOfMembersWithManyCars;
	}

	public void setNumOfMembersWithManyCars(int numOfMembersWithManyCars) {
		this.numOfMembersWithManyCars = numOfMembersWithManyCars;
	}

	public int getNumOfMembers() {
		return numOfMembers;
	}

	public void setNumOfMembers(int numOfMembers) {
		this.numOfMembers = numOfMembers;
	}

}
