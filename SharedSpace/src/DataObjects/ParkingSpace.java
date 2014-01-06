package DataObjects;

import java.io.Serializable;

public class ParkingSpace implements Serializable{
	private boolean disabled;
	private boolean reserved;
	private boolean occupied;
	private String carNum;
	
	
	public ParkingSpace() {
		super();
		disabled = false;
		reserved = false;
		occupied = false;
		carNum = null;
	}
	
	public ParkingSpace(boolean disabled, boolean reserved, boolean occupied,
			String carNum) {
		super();
		this.disabled = disabled;
		this.reserved = reserved;
		this.occupied = occupied;
		this.carNum = carNum;
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
	
	
}
