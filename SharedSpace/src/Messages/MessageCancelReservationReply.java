package Messages;

import javax.swing.JOptionPane;

public class MessageCancelReservationReply extends Message {
	private String reservationNum;
	private boolean completed;
	

	
	public MessageCancelReservationReply(String reservationNum, boolean completed) {
		this.reservationNum = reservationNum;
		this.completed = completed;
	}
	
	

	public boolean isCompleted() {
		return completed;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		if (completed == true)
		{
			JOptionPane.showMessageDialog(null, "Reservation number" + reservationNum + " canceled.");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Reservation number" + reservationNum + " doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}
