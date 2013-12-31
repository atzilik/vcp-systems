package Messages;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DataObjects.Customer;
import DataObjects.Worker;



public class MessageLoginReply extends Message {

	private Customer cust;
	private Worker wrk;
	
	public MessageLoginReply(){
		cust = null;
		wrk = null;
	}
	
	public MessageLoginReply(Customer cust){
		this.cust = cust;

	}
	
	public MessageLoginReply(Worker wrk){
		this.wrk = wrk;
	}
	
	public Customer getCust() {
		return cust;
	}


	public Worker getWrk() {
		return wrk;
	}


	public boolean isEmpty(){
		if (cust == null && wrk == null)
			return true;
		return false;
	}
	
	public boolean isCustomer(){
		if (cust != null)
			return true;
		return false;
	}
	
	public Message doAction() {
		if (isEmpty() == true)
		{
			JOptionPane.showMessageDialog(null, "Wrong username or password.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Login successful.");
		}
		return null;
		
	}

}
