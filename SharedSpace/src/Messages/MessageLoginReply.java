package Messages;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import DataObjects.Customer;
import DataObjects.Worker;



public class MessageLoginReply extends Message {

	private Customer cust;
	private Worker wrk;
	private boolean isCustomer;
	private boolean isWorker;
	
	public MessageLoginReply(){
		isCustomer = false;
		isWorker = false;
	}
	
	public MessageLoginReply(Customer cust){
		this.cust = cust;
		isCustomer = true;
		isWorker = false;
	}
	
	public MessageLoginReply(Worker wrk){
		this.wrk = wrk;
		isCustomer = false;
		isWorker = true;
	}
//	@Override
//	public Message doAction() {
//		// TODO Auto-generated method stub
//		if (isCustomer == true)
//		{
//			CustomerMenu cm = new CustomerMenu((Customer)cust);
//			cm.setVisible(true);
//		}
//		else if (isWorker == true)
//		{
//			WorkerMenu wr = new WorkerMenu((Worker)wrk);
//			wr.setVisible(true);
//		}
//		else
//		{
//			JOptionPane.showMessageDialog(new JPanel(), "Wrong user name or password.", "Error", JOptionPane.ERROR_MESSAGE);
//		}
//		return null;
//	}
	public Message doAction() {
		return null;
		
	}

}
