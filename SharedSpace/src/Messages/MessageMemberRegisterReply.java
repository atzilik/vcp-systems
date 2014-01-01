package Messages;

import javax.swing.JOptionPane;

public class MessageMemberRegisterReply extends Message {
	private String memberID;

	public MessageMemberRegisterReply(){
		memberID = null;
	}

	public MessageMemberRegisterReply(String memberID){
		this.memberID = memberID;
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
			JOptionPane.showMessageDialog(null, "Registration complete. your memberID is " + memberID);
		}
		return null;
	}

}
