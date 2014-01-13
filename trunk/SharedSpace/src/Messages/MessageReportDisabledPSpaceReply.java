package Messages;

import javax.swing.JOptionPane;

/**
 * 
 * @author omri
 *This class is responsible for report a disable parking space.
 */
public class MessageReportDisabledPSpaceReply extends Message {
boolean disabled;

/**
 * 
 * @param disabled mark disabled or not
 */
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
