package DataObjects;

import java.sql.Date;
import java.sql.Time;

import Messages.MessageCheckout;
/**
 * represents a standard member
 * @author Gal
 *
 */
public class STDMember extends FullMember {
	private int parkingLotId;
	private Time stdCheckOut;
	
	public STDMember(String id, String carId, String fName, String lName,
			String email, String memberId, Date startDate,
			int parkingLotId, Time stdCheckOut) {
		super(id, carId, fName, lName, email, memberId, startDate);
		this.parkingLotId = parkingLotId;
		this.stdCheckOut = stdCheckOut;
	}


	public int getParkingLotId() {
		return parkingLotId;
	}


	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}


	public Time getStdCheckOut() {
		return stdCheckOut;
	}


	public void setStdCheckOut(Time stdCheckOut) {
		this.stdCheckOut = stdCheckOut;
	}
	
	
	

}
