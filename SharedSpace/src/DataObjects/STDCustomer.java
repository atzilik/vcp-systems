package DataObjects;

public class STDCustomer extends Customer {
	private String estCheckIn;
	private String estCheckOut;

	public STDCustomer(String id, String carId, String fName, String lName,
			String email, String estCheckIn, String estCheckOut) {
		super(id, carId, fName, lName, email);
		this.estCheckIn = estCheckIn;
		this.estCheckOut = estCheckOut;
	}

	public String getEstCheckIn() {
		return estCheckIn;
	}

	public void setEstCheckIn(String estCheckIn) {
		this.estCheckIn = estCheckIn;
	}

	public String getEstCheckOut() {
		return estCheckOut;
	}

	public void setEstCheckOut(String estCheckOut) {
		this.estCheckOut = estCheckOut;
	}


}
