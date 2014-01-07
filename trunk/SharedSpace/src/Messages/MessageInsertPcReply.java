package Messages;

import javax.swing.JOptionPane;

import DataObjects.Reservation;

public class MessageInsertPcReply extends Message{
	private Reservation res;
	public MessageInsertPcReply(){
		res = null;
	}
	
	public MessageInsertPcReply(Reservation res){
		this.res = res;
	}
	
	
	
	public Reservation getRes() {
		return res;
	}

	@Override
	public Message doAction() {
		if (res == null)
		{
			JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Have a nice day.");
		}

		return null;
	}

}
