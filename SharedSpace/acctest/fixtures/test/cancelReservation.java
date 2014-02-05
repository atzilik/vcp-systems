package test;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;

import DataObjects.DateConvert;
import DataObjects.Reservation;
import Messages.MessageCancelReservation;
import fit.ActionFixture;



public class cancelReservation extends ActionFixture {
	private Reservation res;
	private MessageCancelReservation msg;
	public cancelReservation(){
		res = new Reservation();
		res.setRid("100000");
	}
	public void customerId(String cusID) throws Exception
	{
		res.setCid(cusID);
		res.setCancel(1);
	}
	public void carNo(int carNo)
	{
		res.setCarId(carNo);
	}
	public void parkingLot(String plot)
	{
		res.setPl(plot);
	}

	public void addMinutes(int minutes)
	{
		Date checkInDate = DateConvert.addMinutes(DateConvert.getCurrentSqlDate(), minutes);
		Time checkInHour = DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), minutes);
		Date checkOutDate = DateConvert.addMinutes(DateConvert.getCurrentSqlDate(), minutes + 60);
		Time checkOutTime = DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), minutes + 60);
		res.setEstCinDate(checkInDate); // add the date to the reservation
		res.setEstCinHour(checkInHour); // add the time to the reservation
		res.setEstCoutDate(checkOutDate);
		res.setEstCoutHour(checkOutTime);
	}


	public void createNewReservation() throws Exception
	{
		res.setInAdvance(true);
		res.setEstBill(5);
		res.setUsed(false);
		res.setReservationDate(res.getEstCinDate());
	}

	public double cancel() throws SQLException
	{
		msg = new MessageCancelReservation(res);
		
		double fine = msg.calculateRefund(res.getEstCinDate(), res.getEstCinHour(), res.getEstBill());
		return fine;
	}
	
	public boolean checkReservationCancel() throws SQLException
	{
		 if (msg.getRes().getCancel() == 3)
			 return true;
		 else 
			 return false;
	}





}

