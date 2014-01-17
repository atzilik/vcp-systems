package Messages;

import javax.swing.JOptionPane;

import DataObjects.Customer;
/**
 * holds customer details if login went ok.
 * @author Gal
 *
 */
public class MessageCustomerLoginReply extends Message {
	private Customer customer;
	
	public MessageCustomerLoginReply(){
		customer = null;
	}
	
	public MessageCustomerLoginReply(Customer customer){
		this.customer = customer;
	}
	
	

	public Customer getCustomer() {
		return customer;
	}
 /**
  * pop up a window according to the transaction
  */
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (customer == null)
		{
			JOptionPane.showMessageDialog(null, "Wrong id or Car Number.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Login successful.");
		}
		return null;
	}

}

