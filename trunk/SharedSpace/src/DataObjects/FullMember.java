package DataObjects;

import java.sql.Date;
/**
 * represents a full member
 * @author Gal
 *
 */
public class FullMember extends Customer {
	protected String memberId;
	protected Date startDate;
	
	public FullMember(String id, String carId, String fName, String lName,
			String email, String memberId, Date startDate) {
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}	

}
