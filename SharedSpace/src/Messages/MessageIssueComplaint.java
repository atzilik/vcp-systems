package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

/**
 * 
 * @author Gal
 *This class is responsible for issue a customer complaint.
 */
public class MessageIssueComplaint extends Message {
	private String[] details;

	/**
	 * 
	 * @param details of the complaint
	 */
	public MessageIssueComplaint(String[] details){
		this.details = details;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO complaints (complaintID,customerID,details,dateRecv,parkingLotID) VALUES(?,?,?,?,?);");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			ps.setString(3, details[2]);
			ps.setString(4, details[3]);
			ps.setString(5, details[4]);
			ps.executeUpdate();
			ps.close();
			return new MessageIssueComplaintReply(details[0]);  // with all of the complaint details
		}catch (SQLException e) {

			if (e.getMessage().contains("Duplicate entry"))
			{
				details[0] = Integer.toString(100000 + new Random().nextInt(900000)); // genarte complaint number
				new MessageIssueComplaint(details).doAction();
			}		
		}
		return null;
	}

}
