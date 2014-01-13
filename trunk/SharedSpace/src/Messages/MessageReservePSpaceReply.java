package Messages;

import javax.swing.JOptionPane;

/**
 * 
 * @author omri
 *This class is responsible for reserving a parking space for a customer by the worker.
 */
public class MessageReservePSpaceReply extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "ParkingSpace is now reserved...");
		return null;
	}

}
