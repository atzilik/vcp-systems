package Messages;

import javax.swing.JOptionPane;

import DataObjects.ParkingLot;
import DataObjects.Robot;


public class MessageSetupPLReply extends Message {
	private int parkingLotID;
	private int depthSize;
	private boolean updateDepthSize;
	private boolean error = false;
	
	public MessageSetupPLReply() {
		error = true;
	}
	
	public MessageSetupPLReply(int parkingLotID) {
		this.parkingLotID = parkingLotID;
		updateDepthSize = false;
	}

	public MessageSetupPLReply(int parkingLotID, int depthSize) {
		this.parkingLotID = parkingLotID;
		this.depthSize = depthSize;
		updateDepthSize = true;
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (error)
		{
			JOptionPane.showMessageDialog(null, "Parking lot is active already.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else if (updateDepthSize)
		{
			JOptionPane.showMessageDialog(null, "Parking lot "+ parkingLotID + " depth size changed to " + depthSize + " and now active.");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Parking lot "+ parkingLotID + " now active.");
		}
		return null;
	}
	
	

}
