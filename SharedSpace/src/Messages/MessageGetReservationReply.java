package Messages;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

import DataObjects.Reservation;


public class MessageGetReservationReply extends Message{
	private Reservation res;
	private int ans;
	private boolean late;
	
//	public MessageGetReservationReply() {
//		res = null;
//	}
	public MessageGetReservationReply(Reservation res, boolean late) {
		this.res = res;
		this.late = late;
	}
	
	public MessageGetReservationReply(int ans) {
		this.ans = ans;
	}
	
	
	public Reservation getReservation() {
		return res;
	}
	
	
	public boolean isLate() {
		return late;
	}

	@Override
	public Message doAction() {
		
//		if (res == null)
//		{
//			JOptionPane.showMessageDialog(null, "NO reservation reccord.", "Error", JOptionPane.ERROR_MESSAGE);
//		}
		if (ans == 1)
			JOptionPane.showMessageDialog(null, "your early.");
		else if (ans == 2)
			JOptionPane.showMessageDialog(null, "NO reservation reccord.", "Error", JOptionPane.ERROR_MESSAGE);
		else
		{
			JOptionPane.showMessageDialog(null, "Accept.");
		}

		return null;
	}
	

}
