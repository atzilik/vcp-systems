package test;
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
		res.setRid("100000");
	}
	public void customerId(String cusID) throws Exception
	{
		res.setCid(cusID);
	}
	public void carNo(int carNo)
	{
		res.setCarId(carNo);
	}
	public void parkingLot(String plot)
	{
		res.setPl(plot);
	}

	public void addHours(int hours)
	{
		Time currentTimePlus = DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), hours*60);
		Time currentTimeOut = DateConvert.addMinutes(DateConvert.getCurrentSqlTime(), (hours+1)*60);
		res.setEstCinDate(DateConvert.getCurrentSqlDate()); // add the date to the reservation
		res.setEstCinHour(currentTimePlus); // add the time to the reservation
		res.setEstCoutDate(DateConvert.getCurrentSqlDate());
		res.setEstCoutHour(currentTimeOut);
	}


	public void createNewReservation() throws Exception
	{
		res = new Reservation(res.getRid(),res.getCarId(),res.getCid(),res.getPl(),res.getEstCinDate(),res.getEstCinHour(),res.getEstCoutDate(),res.getEstCoutHour(),true,5,false,DateConvert.getCurrentSqlDate());
		res.setCancel(1);
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

