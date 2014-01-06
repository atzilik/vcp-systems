package Messages;

import DataObjects.ParkingLot;
import DataObjects.Robot;


public class MessageSetupPLReply extends Message {
	private ParkingLot parkinglot;
	
	
	
	public MessageSetupPLReply(ParkingLot parkinglot) {
		this.parkinglot = parkinglot;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	

}
