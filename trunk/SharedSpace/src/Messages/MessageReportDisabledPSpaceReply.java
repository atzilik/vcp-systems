package Messages;

import javax.swing.JOptionPane;

public class MessageReportDisabledPSpaceReply extends Message {
boolean disabled;
	public MessageReportDisabledPSpaceReply(boolean disabled) {
	this.disabled = disabled;
}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (disabled==true){
		JOptionPane.showMessageDialog(null, "ParkingSpace is now disabled...");
		}
		else
		{
		JOptionPane.showMessageDialog(null, "ParkingSpace is now enabled...");

		}
		return null;
	}

}
