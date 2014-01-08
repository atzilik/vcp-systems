package Messages;

import javax.swing.JOptionPane;

public class MessageReportDisabledPSpaceReply extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "ParkingSpace is now disabled...");
		return null;
	}

}
