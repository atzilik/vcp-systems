package Messages;

import javax.swing.JOptionPane;

public class MessageAltParkingReply extends Message {
private String AltParkLot;   

public MessageAltParkingReply(String altParkLot) {
	AltParkLot = altParkLot;
}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Alternative parkinglot has been set to " + AltParkLot);
		return null;
	}

}
