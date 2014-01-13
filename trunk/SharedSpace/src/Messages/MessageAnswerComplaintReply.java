package Messages;
/**
 * This is a reply message which will indicate the answered was delivered
 * @author Alon
 *
 */
public class MessageAnswerComplaintReply extends Message {
	/**
	 * message was delivered successfully
	 */
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
