package DataObjects;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class DisabledParkingSpace implements Serializable {
	
	private int ParkingLotID;
	private int floor;
	private int row;
	private int depth;
	private Date disabledDate;
	private Time disabled_time;
	private Date enabledDate;
	private Time enabled_time;
	
	public DisabledParkingSpace(int ParkingLotID, int floor, int row, int depth, Date disabledDate, Time disabled_time, Date enabledDate, Time enabledTime) {
		this.ParkingLotID = ParkingLotID;
		this.floor = floor;
		this.row = row;
		this.depth = depth;
		this.disabledDate = disabledDate;
		this.disabled_time = disabled_time;
		this.enabledDate = enabledDate;
		this.enabled_time = enabledTime;
	}

	public int getParkingLotID() {
		return ParkingLotID;
	}

	public void setParkingLotID(int parkingLotID) {
		ParkingLotID = parkingLotID;
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

	public Date getDisabledDate() {
		return disabledDate;
	}

	public void setDisabledDate(Date disabledDate) {
		this.disabledDate = disabledDate;
	}

	public Time getDisabled_time() {
		return disabled_time;
	}

	public void setDisabled_time(Time disabled_time) {
		this.disabled_time = disabled_time;
	}

	public Date getEnabledDate() {
		return enabledDate;
	}

	public void setEnabledDate(Date enabledDate) {
		this.enabledDate = enabledDate;
	}

	public Time getEnabled_time() {
		return enabled_time;
	}

	public void setEnabled_time(Time enabled_time) {
		this.enabled_time = enabled_time;
	}

}
