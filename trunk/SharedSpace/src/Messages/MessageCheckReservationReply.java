package Messages;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.FullComplaint;
import DataObjects.Reservation;

/**
 * 
 * @author Boaz
 *This class is responsible for the check reservation of the customers.
 */
public class MessageCheckReservationReply extends Message{
	private ArrayList<Reservation> reservationArray = new ArrayList<>();
	
	public MessageCheckReservationReply() {
		this.reservationArray = null;
	}
	
	/**
	 * 
	 * @param reservationArray list of all of the customer reservation
	 */

	public MessageCheckReservationReply(ArrayList<Reservation> reservationArray) {
		this.reservationArray = reservationArray;
	}

	@Override
	public Message doAction() {
		if (reservationArray == null)
			JOptionPane.showMessageDialog(null, "no resevation record."); // there is no reservation
		return null;
	}
	
	public ArrayList<Reservation> getreservationArray() {
		return reservationArray;
	}

}
