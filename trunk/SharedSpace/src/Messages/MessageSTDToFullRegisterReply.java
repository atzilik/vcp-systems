package Messages;

import javax.swing.JOptionPane;

public class MessageSTDToFullRegisterReply extends MessageMemberRegisterReply {

	public MessageSTDToFullRegisterReply(){
		super();
	}
	
	public MessageSTDToFullRegisterReply(String memberID, String carNum, double bill){
		super(memberID, carNum, bill);
	}
	public Message doAction() {
		if (memberID == null)
		{
			JOptionPane.showMessageDialog(null, "You are already a full member.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "full member Registration complete. Your memberID: " + memberID + " Car number: " + carNum + " bill is " + bill);
		}
		return null;
	}
}
