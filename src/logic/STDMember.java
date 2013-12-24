package logic;

public class STDMember extends FullMember {
	private int parkingLotId;
	private String stdCheckOut;
	
	
	public STDMember(String id, String carId, String fName, String lName,
			String email, String memberId, String startDate, boolean active,
			int parkingLotId, String stdCheckOut) {
		super(id, carId, fName, lName, email, memberId, startDate, active);
		this.parkingLotId = parkingLotId;
		this.stdCheckOut = stdCheckOut;
	}


	public int getParkingLotId() {
		return parkingLotId;
	}


	public void setParkingLotId(int parkingLotId) {
		this.parkingLotId = parkingLotId;
	}


	public String getStdCheckOut() {
		return stdCheckOut;
	}


	public void setStdCheckOut(String stdCheckOut) {
		this.stdCheckOut = stdCheckOut;
	}
	
	
	

}
