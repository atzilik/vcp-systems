package Messages;

import javax.swing.JOptionPane;

public class MessageInsertReservationReply extends Message {
	private String reservationNum;
	private boolean completed;
	private double bill;
	
	public MessageInsertReservationReply(){
		completed = false;
	}
	
	public MessageInsertReservationReply(String reservationNum, double bill){
		this.reservationNum = reservationNum;
		this.bill = bill;
		completed = true;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (completed == true)
		{
			JOptionPane.showMessageDialog(null, "Reservation number " + reservationNum + " taken. Your estimated bill will be " + bill);
		}
		else
		{
			
		}
		return null;
	}

}
