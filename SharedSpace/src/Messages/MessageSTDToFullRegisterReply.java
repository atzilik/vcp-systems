package Messages;

import javax.swing.JOptionPane;

public class MessageSTDToFullRegisterReply extends MessageMemberRegisterReply {

	public MessageSTDToFullRegisterReply(){
		super();
	}
	
	public MessageSTDToFullRegisterReply(String memberID, String carNum){
		super(memberID, carNum);
	}
	public Message doAction() {
		if (memberID == null)
		{
			JOptionPane.showMessageDialog(null, "You are already a full member.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "full member Registration complete. Your memberID: " + memberID + " Car number: " + carNum);
		}
		return null;
	}
}
