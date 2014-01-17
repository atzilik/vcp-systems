package DataObjects;

import java.io.Serializable;

/**
 * represents an ordinary customer
 * @author Gal
 *
 */
public class Customer implements Serializable{
	protected String id;
	protected String carId;
	protected String fName;
	protected String lName;
	protected String email;
	

	public Customer(String id, String carId, String fName,
			String lName, String email) {
		this.id = id;
		this.carId = carId;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
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

}
