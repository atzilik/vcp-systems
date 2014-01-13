package DataObjects;

import java.io.Serializable;
import java.util.Date;
/**
 * This class represents a full complaint with all the details
 * @author Alon
 *
 */
public class FullComplaint implements Serializable {
	/**
	 * complaint number
	 */
	private int complaintID;
	/**
	 * customer ID
	 */
	private int customerID;
	/**
	 * the complaint details
	 */
	private String details;
	/**
	 * the date the complaint was received
	 */
	private Date dateRecv;
	/**
	 * refund ammount
	 */
	private String refund;
	/**
	 * date the complaint was handled
	 */
	private Date dateHandled;
	
	private String workerID;
	private String answer;
	/**
	 * loads the compaint data
	 * @param complaintID
	 * @param customerID
	 * @param details
	 * @param dateRecv
	 * @param refund
	 * @param dateHandled
	 * @param workerID
	 * @param answer of the cutomer service
	 */
	public FullComplaint (int complaintID, int customerID, String details, Date dateRecv, String refund, Date dateHandled, String workerID, String answer) {
		this.complaintID = complaintID;
		this.customerID = customerID;
		this.details = details;
		this.dateRecv = dateRecv;
		this.refund = refund;
		this.dateHandled = dateHandled;
		this.workerID = workerID;
		this.answer = answer;
	}
	
	public int getComplaintID() {
		return complaintID;
	}
	public void setComplaintID(int complaintID) {
		this.complaintID = complaintID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public Date getDateRecv() {
		return dateRecv;
	}
	public void setDateRecv(Date dateRecv) {
		this.dateRecv = dateRecv;
	}
	public String getRefund() {
		return refund;
	}
	public void setRefund(String refund) {
		this.refund = refund;
	}
	public Date getDateHandled() {
		return dateHandled;
	}
	public void setDateHandled(Date dateHandled) {
		this.dateHandled = dateHandled;
	}
	public String getWorkerID() {
		return workerID;
	}
	public void setWorkerID(String workerID) {
		this.workerID = workerID;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	

}
