package Messages;

import javax.swing.JOptionPane;

public class MessageReservePSpaceReply extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "ParkingSpace is now reserved...");
		return null;
	}

}
