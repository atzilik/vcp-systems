package Messages;

import javax.swing.JOptionPane;

public class MessageIssueComplaintReply extends Message {
	private String complaintNum;

	
	public MessageIssueComplaintReply(String complaintNum){
		this.complaintNum = complaintNum;
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Complaint number " + complaintNum + " taken.");
		return null;
	}

}
