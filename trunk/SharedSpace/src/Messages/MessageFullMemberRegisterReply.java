package Messages;

import javax.swing.JOptionPane;

public class MessageFullMemberRegisterReply extends Message {
	private String memberID;

	public MessageFullMemberRegisterReply(){
		memberID = null;
	}

	public MessageFullMemberRegisterReply(String memberID){
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
