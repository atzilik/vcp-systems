package Messages;

import javax.swing.JOptionPane;

public class MessageDisableFacilityReply extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Facility is now disabled", "Error", JOptionPane.ERROR_MESSAGE);
		return null;
	}

}
