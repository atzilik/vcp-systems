package DataObjects;

public class STDCustomer extends Customer {
	private String memberID;
	private boolean registeredToMember;
	public STDCustomer(String id, String carId, String fName, String lName,
			String email) {
		super(id, carId, fName, lName, email);
		memberID = null;
		registeredToMember = false;
		// TODO Auto-generated constructor stub
	}
	public String getMemberID() {
		return memberID;
	}
	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
	public boolean isRegisteredToMember() {
		return registeredToMember;
	}
	public void setRegisteredToMember(boolean registeredToMember) {
		this.registeredToMember = registeredToMember;
	}
	
	

}
