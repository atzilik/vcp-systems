package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Complaint;
import DataObjects.Worker;

public class MessageAnswerComplaint extends Message {
	
	private Connection sqlConn;
	private Complaint complaint;
	private String refund;
	private String answer;
	private Worker wkr;

	public MessageAnswerComplaint(Complaint comp, String ref, String ans, Worker wkr){
		this.complaint = comp;
		this.refund = ref;
		this.answer = ans;
		this.wkr = wkr;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("UPDATE complaints SET refund=?,dateHandled=?,workerID=?,answer=? WHERE ComplaintID=?;");
			ps.setString(1, this.refund);
			ps.setString(2, new java.sql.Date(new java.util.Date().getTime()).toString());
			ps.setString(3, wkr.getId());
			ps.setString(4, answer);
			ps.setString(5, complaint.getComplaintID());
			ps.executeUpdate();
			
	
			return new MessageAnswerComplaintReply(true);	
		}
		catch (SQLException e) {
			return new MessageAnswerComplaintReply(false);
		}
	}

}
