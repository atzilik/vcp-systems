package Messages;

import javax.swing.JOptionPane;
/** 
 * This is a reply message which will show the worker the updated alt parking lot
 * @author Alon
 *
 */
public class MessageAltParkingReply extends Message {
	/**
	 * the alt parking lot details
	 */
	private String AltParkLot;   

public MessageAltParkingReply(String altParkLot) {
	AltParkLot = altParkLot;
}
	@Override
	/**
	 * show a message dialog to user 
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Alternative parkinglot has been set to " + AltParkLot);
		return null;
	}

}
