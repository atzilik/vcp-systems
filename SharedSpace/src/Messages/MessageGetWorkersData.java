package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DataObjects.Worker;

public class MessageGetWorkersData extends Message {
	
	private Connection sqlConn;
	private ArrayList<Worker> workerArray = new ArrayList<>();

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM workers");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				workerArray.add(new Worker(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5))); 
			
			return new MessageGetWorkersDataReply(workerArray);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}

	}
}
