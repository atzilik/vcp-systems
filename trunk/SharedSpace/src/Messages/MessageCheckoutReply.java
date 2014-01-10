package Messages;

import javax.swing.JOptionPane;

import DataObjects.Customer;
import DataObjects.STDCustomer;

public class MessageCheckoutReply extends Message {
	private Customer customer;
	private int parkingLotID;
	private boolean empty;
	private double bill;
	private int floor;
	private int row;
	private int depth;
	
	public MessageCheckoutReply(Customer customer){
		this.customer = customer;
		empty = true;
	}
	
	public MessageCheckoutReply(Customer customer, int parkingLotID, double bill, int floor, int row, int depth){
		this.customer = customer;
		this.parkingLotID = parkingLotID;
		this.bill = bill;
		this.floor = floor;
		this.row = row;
		this.depth = depth;
		empty = false;
	}
	
	
	public boolean isEmpty() {
		return empty;
	}

	

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getParkingLotID() {
		return parkingLotID;
	}

	public void setParkingLotID(int parkingLotID) {
		this.parkingLotID = parkingLotID;
	}
	
	

	public int getFloor() {
		return floor;
	}

	public int getRow() {
		return row;
	}

	public int getDepth() {
		return depth;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (empty == true)
		{
			JOptionPane.showMessageDialog(null, "Error. car number " + customer.getCarId() + " didn't checked in.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			if (customer instanceof STDCustomer)
			{
				JOptionPane.showMessageDialog(null, "Check out successful. your bill is " + bill);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Check out successful.");
			}
		}
		return null;
	}

}
