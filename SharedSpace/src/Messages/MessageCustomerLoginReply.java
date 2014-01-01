package Messages;

import javax.swing.JOptionPane;

import DataObjects.Customer;

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

