package Messages;

import java.util.Map;
/**
 * 
 * @author Gal
 *This class is responsible for converting the combo box choice into string.
 *mp contains a hash of the name of the parking lot and the number of them
 */
public class MessageGetParkingLotsIDReply extends Message {
	private Map<String, Integer> mp;
	/**
	 * 
	 * @param mp describe above
	 */
	public MessageGetParkingLotsIDReply(Map<String, Integer> mp){
		this.mp = mp;
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Integer> getMp() {
		return mp;
	}
}
