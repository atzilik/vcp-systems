package Messages;

import javax.swing.JOptionPane;

/**
 * 
 * @author omri
 *This class is responsible for request to change a rate for pl by employer.
 */
public class MessageRequestChangeParkingRateReply extends Message {

	boolean isDone;
	/**
	 * 
	 * @param b is the massege pass
	 */
	public MessageRequestChangeParkingRateReply(boolean b) {
		isDone = b;// TODO Auto-generated constructor stub
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if(isDone)
			JOptionPane.showMessageDialog(null, "Done");
		else
			JOptionPane.showMessageDialog(null, "Error");
		return null;
	}

}
