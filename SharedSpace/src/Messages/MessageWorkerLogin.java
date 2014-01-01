package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataObjects.*;




public class MessageWorkerLogin extends Message {

	private String userName;
	private String password;
	
	public MessageWorkerLogin(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}


	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		con = this.sqlConnection.getConnection();
		try 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE userName=? and password=?;");
			ps.setString(1, userName);
			ps.setString(2, password);
			ResultSet user = ps.executeQuery();

			if (user.isBeforeFirst() == false)
				//user doesn't exist
			{
				user.close();
				ps.close();
				return new MessageWorkerLoginReply();
			}
			else
			{
				user.next();
				ps = con.prepareStatement("SELECT * FROM workers WHERE id=?");
				ps.setString(1, user.getString(3));
				ResultSet wkr = ps.executeQuery();
				wkr.next();
				switch(wkr.getString(6))
				{
				case "1": {
					return new MessageWorkerLoginReply(new CeoWorker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5))); 
				}
				case "2": {
					return new MessageWorkerLoginReply(new ParkingLotManager(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5)));
				}
				case "3": {
					return new MessageWorkerLoginReply(new Worker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5)));
				}
				case "4": {
					return new MessageWorkerLoginReply(new CustomerService(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5)));
				}
				}
//				user.close();
//				wkr.close();
//				ps.close();
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}