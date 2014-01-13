package DataObjects;

import java.io.Serializable;
/**
 * This Class represents a worker.. all the workers in the systems have the same details 
 * @author Alon
 *
 */
public class Worker implements Serializable {
	/**
	 * worker ID
	 */
	protected String id;
	/**
	 * first name
	 */
	protected String fName;
	/**
	 * last name
	 */
	protected String lName;
	/**
	 * email
	 */
	protected String email;
	/**
	 * parkingLotID
	 */
	protected int parkingLotID;
	/**
	 * loads a worker with pesonal details
	 * @param id 
	 * @param fName first name
	 * @param lName last name
	 * @param email email
	 * @param parkingLotID 
	 */
	public Worker(String id, String fName, String lName, String email,
		int parkingLotID) {
		super();
		this.id = id;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.parkingLotID = parkingLotID;
		
		
	}

	public Worker() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getParkingLotID() {
		return parkingLotID;
	}

	public void setParkingLotID(int parkingLotID) {
		this.parkingLotID = parkingLotID;
	}
	
	
}
