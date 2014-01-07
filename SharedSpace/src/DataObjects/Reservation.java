package DataObjects;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Date;

public class Reservation implements Serializable{
	protected String rid;
	protected int carId;
	protected String cid;
	protected String pl;
	protected Date estCinDate;
	protected Time estCinHour;
	protected Date estCoutDate;
	protected Time estCoutHour;

	public Reservation (String rid, int carId, String cid, String pl, Date estCinDate, 
			Time estCinHour, Date estCoutDate, Time estCoutHour) {
		this.rid = rid;
		this.carId = carId;
		this.cid = cid;
		this.pl = pl;
		this.estCinDate = estCinDate;
		this.estCinHour = estCinHour;
		this.estCoutDate = estCoutDate;
		this.estCoutHour = estCoutHour;
		
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
	public Date getEstCinDate() {
		return estCinDate;
	}
	public Time getEstCinHour() {
		return estCinHour;
	}
	public Date getEstCoutDate() {
		return estCoutDate;
	}
	public Time getEstCoutHour() {
		return estCoutHour;
	}
}
