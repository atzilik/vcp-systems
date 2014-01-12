package DataObjects;

import java.io.Serializable;

public class Complaint implements Serializable {

	private String complaintID;
	private String customerID;
	private String details;
	private String date;
	private String parkingLotID;
	
	public Complaint(String comID, String custID, String det, String dat, String parkingLotID) {
		this.complaintID = comID;
		this.customerID = custID;
		this.details = det;
		this.date = dat;
		this.parkingLotID = parkingLotID;
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
	
	
}
