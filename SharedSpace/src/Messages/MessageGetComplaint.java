package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DataObjects.Complaint;
import DataObjects.RateRequest;

public class MessageGetComplaint extends Message {
	
	private Connection sqlConn;
	private ArrayList<Complaint> ComplaintsArray = new ArrayList<>();

	@Override
	public Message doAction() {
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM complaints");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				if(rs.getString(6)==null)
					ComplaintsArray.add(new Complaint(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(9))); 
			
			return new MessageGetComplaintReply(ComplaintsArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
