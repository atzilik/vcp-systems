package DataObjects;

public class FullMember extends Customer {
	protected String memberId;
	protected String startDate;
	
	public FullMember(String id, String carId, String fName, String lName,
			String email, String memberId, String startDate) {
		super(id, carId, fName, lName, email);
		this.memberId = memberId;
		this.startDate = startDate;
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

}
