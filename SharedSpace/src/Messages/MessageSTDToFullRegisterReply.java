package Messages;

import javax.swing.JOptionPane;

/**
 * 
 * @author Gal
 *This class is responsible for upgrading std member to full member.
 */
public class MessageSTDToFullRegisterReply extends MessageMemberRegisterReply {

	public MessageSTDToFullRegisterReply(){
		super();
	}
	
	/**
	 * 
	 * @param memberID
	 * @param carNum
	 * @param bill rate price hour of the customer 
	 */
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
