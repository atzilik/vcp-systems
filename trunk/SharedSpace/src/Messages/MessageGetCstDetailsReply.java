package Messages;

import DataObjects.Customer;
import DataObjects.Worker;

public class MessageGetCstDetailsReply extends Message {
	private Customer customer;
	
	public MessageGetCstDetailsReply(Customer customer){
		this.customer = customer;
	}
	
	

	public Customer getCustomer() {
		return customer;
	}



	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		return null;
	}

}
