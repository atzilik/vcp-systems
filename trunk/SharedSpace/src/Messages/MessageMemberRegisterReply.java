package Messages;

import javax.swing.JOptionPane;

public class MessageMemberRegisterReply extends Message {
	protected String memberID;
	protected String carNum;
	protected double bill;

	public MessageMemberRegisterReply(){
		memberID = null;
	}

	public MessageMemberRegisterReply(String memberID, String carNum, double bill){
		this.memberID = memberID;
		this.carNum = carNum;
		this.bill = bill;
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
			JOptionPane.showMessageDialog(null, "Registration complete. Your memberID: " + memberID + " Car number: " + carNum + " bill: " + bill);
		}
		return null;
	}

}
