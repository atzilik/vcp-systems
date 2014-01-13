package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.RateRequest;
import DataObjects.Reservation;
import DataObjects.Worker;
/**
 * This reply message is responsible of sending the data back to the gui
 * @author Alon
 *
 */
public class MessageGetReservationDataReply extends Message {
	/**
	 * array list which holds the current reservaion data
	 */
	private ArrayList<Reservation> reservationsArray;
	private Worker wkr;
	/**
	 * loads the reservation data to the reservationsArray 
	 * @param reservationsArray
	 */
	public MessageGetReservationDataReply(ArrayList<Reservation> reservationsArray) {
		
		this.setReservationsArray(reservationsArray);
	}
	
	public MessageGetReservationDataReply(){
	}

	@Override
	public Message doAction() {
		return null;
	}

	public ArrayList<Reservation> getReservationsArray() {
		return reservationsArray;
	}

	public void setReservationsArray(ArrayList<Reservation> reservationsArray) {
		this.reservationsArray = reservationsArray;
	}


}