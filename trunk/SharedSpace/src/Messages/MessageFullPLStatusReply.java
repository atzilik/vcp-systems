package Messages;

import javax.swing.JOptionPane;

public class MessageFullPLStatusReply extends Message {
	private int parkingLotID;
	private boolean full;
	
	public MessageFullPLStatusReply(int parkingLotID, boolean full){
		this.parkingLotID = parkingLotID;
		this.full = full;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (full)
		{
			JOptionPane.showMessageDialog(null, "Parking lot " + parkingLotID + " set to full.");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Parking lot " + parkingLotID + " change to not full.");
		}
		return null;
	}

}
