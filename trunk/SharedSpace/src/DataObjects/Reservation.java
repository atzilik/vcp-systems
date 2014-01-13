package DataObjects;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.sql.Date;


public class Reservation implements Serializable{
	protected String rid;
	protected int carId;
	protected String cid;
	protected String pl;
	protected java.sql.Date estCinDate;
	protected Time estCinHour;
	protected java.sql.Date estCoutDate;
	protected Time estCoutHour;
	protected boolean inAdvance;
	protected double estBill;
	protected boolean used;
	protected java.sql.Date reservationDate;

	
	public Reservation (String rid, int carId, String cid, String pl, java.sql.Date estCinDate, 
			Time estCinHour, java.sql.Date estCoutDate, Time estCoutHour, boolean inAdvance, double estBill, boolean used,java.sql.Date reservationDate ) {
		this.rid = rid;
		this.carId = carId;
		this.cid = cid;
		this.pl = pl;
		this.estCinDate = estCinDate;
		this.estCinHour = estCinHour;
		this.estCoutDate = estCoutDate;
		this.estCoutHour = estCoutHour;
		this.inAdvance = inAdvance;
		this.estBill = estBill;
		this.used = used;
		this.reservationDate = reservationDate;
	}
	


	public String getRid() {
		return rid;
	}
	public int getCarId() {
		return carId;
	}
	public String getCid() {
		return cid;
	}
	public String getPl() {
		return pl;
	}
	public java.sql.Date getEstCinDate() {
		return estCinDate;
	}
	public Time getEstCinHour() {
		return estCinHour;
	}
	public java.sql.Date getEstCoutDate() {
		return estCoutDate;
	}
	public Time getEstCoutHour() {
		return estCoutHour;
	}
	public boolean isInAdvance() {
		return inAdvance;
	}
	public void setInAdvance(boolean inAdvance) {
		this.inAdvance = inAdvance;
	}
	public double getEstBill() {
		return estBill;
	}
	public void setEstBill(double estBill) {
		this.estBill = estBill;
	}
	public boolean isUsed() {
		return used;
	}
	public void setUsed(boolean used) {
		this.used = used;
	}
	public java.sql.Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(java.sql.Date reservationDate) {
		this.reservationDate = reservationDate;
	}

}
