package Messages;

import javax.swing.JOptionPane;

public class MessageInsertReservationReply extends Message {
	private String reservationNum;
	private boolean completed;
	
	public MessageInsertReservationReply(){
		completed = false;
	}
	
	public MessageInsertReservationReply(String reservationNum){
		this.reservationNum = reservationNum;
		completed = true;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (completed == true)
		{
			JOptionPane.showMessageDialog(null, "Reservation number " + reservationNum + " taken.");
		}
		else
		{
			
		}
		return null;
	}

}
