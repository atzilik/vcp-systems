package DataObjects;

public class FullMember extends Customer {
	protected String memberId;
	protected String startDate;
	protected boolean active;
	
	public FullMember(String id, String carId, String fName, String lName,
			String email, String memberId, String startDate, boolean active) {
		super(id, carId, fName, lName, email);
		this.memberId = memberId;
		this.startDate = startDate;
		this.active = active;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
