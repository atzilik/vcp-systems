package Messages;

import DataObjects.ParkingLot;
/**
 * a reply for the mapping of the parking lot. this class holds the mapped parking lot.
 * @author Gal
 *
 */
public class MessageMapParkingLotReply extends Message {
	private ParkingLot[] parkinglot;
	
	public MessageMapParkingLotReply(ParkingLot[] parkinglot){
		this.parkinglot = parkinglot;
	}
	
	
	public ParkingLot[] getParkinglot() {
		return parkinglot;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
