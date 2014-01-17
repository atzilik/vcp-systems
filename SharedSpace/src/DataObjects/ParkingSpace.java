package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
/**
 * represents a single parking space in parking lot
 * @author Gal
 *
 */
public class ParkingSpace implements Serializable{
	/**
	 * floor index
	 */
	private int floor;
	/**
	 * row index
	 */
	private int row;
	/**
	 * depth index
	 */
	private int depth;
	private boolean disabled;
	private boolean reserved;
	private boolean occupied;
	/**
	 * car number if the parking space is occupied
	 */
	private int carNum;
	private Date checkOutdate;
	private Time checkOutTime;
	
	/**
	 * constructor when receiving only index parameters, used when mapping an empty parking space
	 * @param floor index
	 * @param row index
	 * @param depth index
	 */
	public ParkingSpace(int floor, int row, int depth) {
		this.floor = floor;
		this.row = row;
		this.depth = depth;
		disabled = false;
		reserved = false;
		occupied = false;
		
	}
	
	/**
	 * constructor when mapping a non empty parking space
	 * @param floor index
	 * @param row index
	 * @param depth index
	 * @param disabled 
	 * @param reserved
	 * @param occupied
	 * @param carNum
	 * @param checkOutdate
	 * @param checkOutTime
	 */
	public ParkingSpace(int floor, int row, int depth, boolean disabled, boolean reserved, boolean occupied,
			int carNum, Date checkOutdate, Time checkOutTime) {
		this.floor = floor;
		this.row = row;
		this.depth = depth;
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


	public int getCarNum() {
		return carNum;
	}


	public void setCarNum(int carNum) {
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

	
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	
	public boolean isAvailable(){
		if (isDisabled() == false && isReserved() == false && isOccupied() == false)
			return true;
		return false;
	}
	
	public boolean isEmpty(){
		if (isDisabled() == false && isReserved() == false)
			return true;
		return false;
	}
	
	public boolean isParkable(){
		if (isDisabled() == false && isOccupied() == false)
			return true;
		return false;
	}
	
}
