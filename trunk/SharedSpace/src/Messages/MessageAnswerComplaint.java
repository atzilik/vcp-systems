package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import DataObjects.Complaint;
import DataObjects.DateConvert;
import DataObjects.Worker;
/**
 * This message is responsible of updating the details of the answer for the complaint
 * @author Alon
 *
 */
public class MessageAnswerComplaint extends Message {
	/**
	 * connection to db
	 */
	private Connection sqlConn;
	/**
	 * The complaint itself
	 */
	private Complaint complaint;
	/**
	 * the refund decided
	 */
	private String refund;
	/**
	 * answer of customer service
	 */
	private String answer;
	/**
	 * worker
	 */
	private Worker wkr;
/**
 * loads the message details
 * @param comp the complaint itself
 * @param ref refund
 * @param ans answer
 * @param wkr worker
 */
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
			
			ps.close();
			ps = sqlConn.prepareStatement("INSERT INTO customer_bill (customerID,carID,date,time,sum) VALUES (?,?,?,?,?);");
			ps.setString(1, complaint.getCustomerID());
			ps.setString(2, complaint.getCarID());
			ps.setDate(3, DateConvert.getCurrentSqlDate());
			ps.setTime(4, DateConvert.getCurrentSqlTime());
			ps.setString(5, refund);
			ps.executeUpdate();
			ps.close();
			
			return new MessageAnswerComplaintReply(true);	
		}
		catch (SQLException e) {
			e.printStackTrace();
			return new MessageAnswerComplaintReply(false);
		}
	}

}
