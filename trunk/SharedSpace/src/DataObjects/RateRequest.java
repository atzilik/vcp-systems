package DataObjects;

import java.io.Serializable;
/**
 * loads all the needed details for a rate request
 * @author Alon
 *
 */
public class RateRequest implements Serializable {
	/**
	 * parking lot ID
	 */
	private int ParkingLotId;
	/**
	 * occasional fee
	 */
	private float occasional;
	/**
	 * reserved fee
	 */
	private float reserved;
	/**
	 * standard membership fee
	 */
	private float standard;
	/**
	 * full membership fee
	 */
	private float full;
	/**
	 * The requesting manager ID
	 */
	private String MangerId;
	/**
	 * loads the details of the requested fee changes
	 * @param pId parking lot ID
	 * @param occ occasional fee
	 * @param res reserved fee
	 * @param std standard membership fee
	 * @param full full membership fee
	 * @param mngId The requesting manager ID
	 */
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
