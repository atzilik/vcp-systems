package Messages;

import java.util.ArrayList;

import DataObjects.Complaint;

public class MessageGetComplaintReply extends Message {
	
	private ArrayList<Complaint> complaintsArray;

	public MessageGetComplaintReply(ArrayList<Complaint> complaintsArray) {
		// TODO Auto-generated constructor stub
		this.setComplaintsArray(complaintsArray);
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Complaint> getComplaintsArray() {
		return complaintsArray;
	}

	public void setComplaintsArray(ArrayList<Complaint> complaintsArray) {
		this.complaintsArray = complaintsArray;
	}
}
