package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.RateRequest;
import DataObjects.Reservation;
import DataObjects.Worker;

public class MessageGetReservationDataReply extends Message {
	
	private ArrayList<Reservation> reservationsArray;
	private Worker wkr;
	
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