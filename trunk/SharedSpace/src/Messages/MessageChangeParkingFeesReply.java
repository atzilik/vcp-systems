package Messages;

public class MessageChangeParkingFeesReply extends Message {

	private boolean transactionSucceeded = false;
	
	public MessageChangeParkingFeesReply() {
		
		this.MessageType = "MessageChangeParkingFeesReply";
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return this;
	}

	public boolean isTransactionSucceeded() {
		return transactionSucceeded;
	}

	public void setTransactionSucceeded(boolean transactionSucceeded) {
		this.transactionSucceeded = transactionSucceeded;
	}

}
