package Messages;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.FullComplaint;
import DataObjects.Reservation;

public class MessageCheckReservationReply extends Message{
	private ArrayList<Reservation> reservationArray = new ArrayList<>();
	
	public MessageCheckReservationReply() {
		this.reservationArray = null;
	}

	public MessageCheckReservationReply(ArrayList<Reservation> reservationArray) {
		this.reservationArray = reservationArray;
	}

	@Override
	public Message doAction() {
		if (reservationArray == null)
			JOptionPane.showMessageDialog(null, "no resevation record.");
		return null;
	}
	
	public ArrayList<Reservation> getreservationArray() {
		return reservationArray;
	}

}
