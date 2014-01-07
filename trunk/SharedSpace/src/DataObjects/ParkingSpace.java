package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class ParkingSpace implements Serializable{
	private boolean disabled;
	private boolean reserved;
	private boolean occupied;
	private String carNum;
	private Date checkOutdate;
	private Time checkOutTime;
	
	
	public ParkingSpace() {
		super();
		disabled = false;
		reserved = false;
		occupied = false;
		carNum = null;
	}
	
	public ParkingSpace(boolean disabled, boolean reserved, boolean occupied,
			String carNum, Date checkOutdate, Time checkOutTime) {
		super();
		this.disabled = disabled;
		this.reserved = reserved;
		this.occupied = occupied;
		this.carNum = carNum;
		this.checkOutdate = checkOutdate;
		this.checkOutTime = checkOutTime;
	}


	public boolean isDisabled() {
		return disabled;
	}


	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}


	public boolean isReserved() {
		return reserved;
	}


	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}


	public boolean isOccupied() {
		return occupied;
	}


	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}


	public String getCarNum() {
		return carNum;
	}


	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public Date getCheckOutdate() {
		return checkOutdate;
	}

	public void setCheckOutdate(Date checkOutdate) {
		this.checkOutdate = checkOutdate;
	}
	
	
	public Time getCheckOutTime() {
		return checkOutTime;
	}

	public void setCheckOutTime(Time checkOutTime) {
		this.checkOutTime = checkOutTime;
	}

	public boolean isAvailable(){
		if (isDisabled() == false && isReserved() == false && isOccupied() == false)
			return true;
		return false;
	}
	
}
