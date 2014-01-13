package Messages;

import java.sql.Connection;
import java.util.ArrayList;

import DataObjects.FullComplaint;
/**
 * This message indicates if the complaint data was received and sends the complaints data to the gui
 * @author Alon
 *
 */
public class MessageGetCompaintDataReply extends Message {

	private ArrayList<FullComplaint> complaintsArray = new ArrayList<>();
	
	public MessageGetCompaintDataReply(ArrayList<FullComplaint> complaintsArray) {
		this.setComplaintsArray(complaintsArray);
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<FullComplaint> getComplaintsArray() {
		return complaintsArray;
	}
	public void setComplaintsArray(ArrayList<FullComplaint> complaintsArray) {
		this.complaintsArray = complaintsArray;
	}

}
