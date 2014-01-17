package Messages;

/**
 * return and valid generated id.
 * @author Gal
 *
 */
public class MessageGenerateValidIDReply extends Message {
	private String iD;
	
	

	public MessageGenerateValidIDReply(String iD) {
		this.iD = iD;
	}



	

	public String getiD() {
		return iD;
	}





	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
