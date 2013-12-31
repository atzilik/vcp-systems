package Messages;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import DataObjects.Worker;



public class MessageLoginReply extends Message {

	private String customerID;
	private Worker wrk;
	
	public MessageLoginReply(){
		customerID = null;
		wrk = null;
	}
	
	public MessageLoginReply(String customerID){
		this.customerID = customerID;

	}
	
	public MessageLoginReply(Worker wrk){
		this.wrk = wrk;
	}
	
	public String getCustomerID() {
		return customerID;
	}


	public Worker getWrk() {
		return wrk;
	}


	public boolean isEmpty(){
		if (customerID == null && wrk == null)
			return true;
		return false;
	}
	
	public boolean isCustomer(){
		if (customerID != null)
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
