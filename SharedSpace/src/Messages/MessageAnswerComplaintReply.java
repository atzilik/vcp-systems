package Messages;

public class MessageAnswerComplaintReply extends Message {

	private boolean isDone;
	public MessageAnswerComplaintReply(boolean b){
		setDone(b);
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}
