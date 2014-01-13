package Messages;

import javax.swing.JOptionPane;
/**
 * This reply message will show a notification to user that the facility is disabled  
 * @author Alon
 *
 */
public class MessageDisableFacilityReply extends Message {

	@Override
	/**
	 * Show the user a notification the the facilty is disabled
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Facility is now disabled", "Error", JOptionPane.ERROR_MESSAGE);
		return null;
	}

}
