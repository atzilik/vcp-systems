package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class MessageIssueComplaint extends Message {
	private String[] details;

	public MessageIssueComplaint(String[] details){
		this.details = details;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("INSERT INTO complaints (complaintID,customerID,details,dateRecv) VALUES(?,?,?,?);");
			ps.setString(1, details[0]);
			ps.setString(2, details[1]);
			ps.setString(3, details[2]);
			ps.setString(4, details[3]);
			ps.executeUpdate();

			return new MessageIssueComplaintReply(details[0]);
		}catch (SQLException e) {

			if (e.getMessage().contains("Duplicate entry"))
			{
				details[0] = Integer.toString(100000 + new Random().nextInt(900000));
				new MessageIssueComplaint(details).doAction();
			}		
		}
		return null;
	}

}
