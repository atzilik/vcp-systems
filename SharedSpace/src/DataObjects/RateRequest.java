package DataObjects;

import java.io.Serializable;

public class RateRequest implements Serializable {
	
	private int ParkingLotId;
	private float occasional;
	private float reserved;
	private float standard;
	private float full;
	private String MangerId;
	
	public RateRequest(int pId, float occ, float res, float std, float full,String mngId) {
		ParkingLotId = pId;
		occasional = occ;
		reserved = res;
		standard = std;
		this.full = full;
		MangerId = mngId;
	}
	
	public int getParkingLotId() {
		return ParkingLotId;
	}
	public void setParkingLotId(int parkingLotId) {
		ParkingLotId = parkingLotId;
	}
	public float getOccasional() {
		return occasional;
	}
	public void setOccasional(float occasional) {
		this.occasional = occasional;
	}
	public float getReserved() {
		return reserved;
	}
	public void setReserved(float reserved) {
		this.reserved = reserved;
	}
	public float getStandard() {
		return standard;
	}
	public void setStandard(float standard) {
		this.standard = standard;
	}
	public float getFull() {
		return full;
	}
	public void setFull(float full) {
		this.full = full;
	}

	public String getMangerId() {
		return MangerId;
	}

	public void setMangerId(String mangerId) {
		MangerId = mangerId;
	}
	
	
	
}
