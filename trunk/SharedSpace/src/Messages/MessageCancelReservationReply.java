package Messages;

import javax.swing.JOptionPane;

public class MessageCancelReservationReply extends Message {
	private String reservationNum;
	private boolean completed;
	private double refund;
	

	
	public MessageCancelReservationReply(String reservationNum, boolean completed, double refund) {
		this.reservationNum = reservationNum;
		this.refund = refund;
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
			JOptionPane.showMessageDialog(null, "Reservation number " + reservationNum + " was canceled. your refund is " + refund);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Reservation number " + reservationNum + " doesn't exist.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}

}
