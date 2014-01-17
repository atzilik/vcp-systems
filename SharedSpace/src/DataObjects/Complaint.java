package DataObjects;

import java.io.Serializable;
/**
 * This class holds the complaint details
 * @author Alon
 *
 */
public class Complaint implements Serializable {
	/**
	 * complaint ID
	 */
	private String complaintID;
	/**
	 * customer ID
	 */
	private String customerID;
	/**
	 * customer complaint details
	 */
	private String details;
	/**
	 * date of customer complaint
	 */
	private String date;
	/**
	 * parking lot Id which the complaint belongs to
	 */
	private String parkingLotID;
	
	/**
	 * customer car id
	 */
	private String carID;
/**
 * loads the customer's complaint details
 */
	public Complaint(String comID, String custID, String det, String dat, String parkingLotID, String carID) {
		this.complaintID = comID;
		this.customerID = custID;
		this.details = det;
		this.date = dat;
		this.parkingLotID = parkingLotID;
		this.carID = carID;
	}


	public String getComplaintID() {
		return complaintID;
	}
	public void setComplaintID(String complaintID) {
		this.complaintID = complaintID;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}


	public String getParkingLotID() {
		return parkingLotID;
	}


	public void setParkingLotID(String parkingLotID) {
		this.parkingLotID = parkingLotID;
	}


	public String getCarID() {
		return carID;
	}


	public void setCarID(String carID) {
		this.carID = carID;
	}
	
	


}
