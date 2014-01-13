package Messages;

import java.util.ArrayList;

import DataObjects.DisabledParkingSpace;

public class MessageGetDisabledParkingSpaceReply extends Message {

	private ArrayList<DisabledParkingSpace> disabledArray = new ArrayList<>();
	
	public MessageGetDisabledParkingSpaceReply(ArrayList<DisabledParkingSpace> disabledArray) {
		this.disabledArray = disabledArray;
	}
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<DisabledParkingSpace> getDisabledArray() {
		return disabledArray;
	}
	public void setDisabledArray(ArrayList<DisabledParkingSpace> disabledArray) {
		this.disabledArray = disabledArray;
	}

}
