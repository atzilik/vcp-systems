package Messages;

import javax.swing.JOptionPane;

public class MessageMemberRegisterReply extends Message {
	protected String memberID;
	protected String carNum;

	public MessageMemberRegisterReply(){
		memberID = null;
	}

	public MessageMemberRegisterReply(String memberID, String carNum){
		this.memberID = memberID;
		this.carNum = carNum;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (memberID == null)
		{
			JOptionPane.showMessageDialog(null, "You are already a member.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Registration complete. Your memberID: " + memberID + " Car number: " + carNum);
		}
		return null;
	}

}
