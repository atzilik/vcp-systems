package Messages;

import javax.swing.JOptionPane;
/**
 * This is a reply message which will indicate if the rate request approve was delivered successfully 
 * @author Alon
 *
 */
public class MessageAprroveRateRequestReply extends Message {
	/**
	 * message was delivered successfully
	 */
	private boolean done;

	public MessageAprroveRateRequestReply(boolean b) {
		setDone(b);
	}
	/**
	 * show a message dialog to user 
	 */
	@Override
	public Message doAction() {
		if(isDone())
			JOptionPane.showMessageDialog(null, "Done");
		else
			JOptionPane.showMessageDialog(null, "Error");
		return null;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

}
