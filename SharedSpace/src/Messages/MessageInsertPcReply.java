package Messages;

import javax.swing.JOptionPane;

import DataObjects.Customer;
import DataObjects.Reservation;
import DataObjects.STDMember;

/**
 * 
 * @author Boaz
 *This class is responsible for insert the reservation into the parking_lot table.
 */
public class MessageInsertPcReply extends Message{
	private Reservation res = null;
	Customer mem = null;
	
//	public MessageInsertPcReply(){
//		res = null;
//	}
	
	/**
	 * 
	 * @param res => std customer
	 */
	public MessageInsertPcReply(Reservation res){
		this.res = res;
	}
	
	/**
	 * 
	 * @param mem => member
	 */
	public MessageInsertPcReply(Customer mem){
		this.mem = mem;
	}
	
	
	
	
	public Reservation getRes() {
		return res;
	}
	
	public Customer getCustomer() {
		return mem;
	}

	@Override
	public Message doAction() {
		if (res == null&&mem == null) 
		{
			JOptionPane.showMessageDialog(null, "Error.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Have a nice day."); // record insert into the table
		}

		return null;
	}

}
