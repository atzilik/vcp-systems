package Messages;

import javax.swing.JOptionPane;

import DataObjects.Customer;
import DataObjects.Reservation;
import DataObjects.STDMember;

public class MessageInsertPcReply extends Message{
	private Reservation res = null;
	Customer mem = null;
	
//	public MessageInsertPcReply(){
//		res = null;
//	}
	
	public MessageInsertPcReply(Reservation res){
		this.res = res;
	}
	
	public MessageInsertPcReply(Customer mem){
		this.mem = mem;
	}
	
	
	
	
	public Reservation getRes() {
		return res;
	}

	@Override
	public Message doAction() {
		if (res == null&&mem == null)
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
