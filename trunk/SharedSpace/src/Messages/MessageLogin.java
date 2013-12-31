package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataObjects.*;




public class MessageLogin extends Message {

	private String userName;
	private String password;
	
	public MessageLogin(String userName, String password) {
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
				return new MessageLoginReply();
			}
			else
			{
				user.next();
				if (user.getString(3) != null)
					//worker
				{
					return loadWorker(user.getString(3), user.getString(5));
				}
				else
					//customer
				{
					return new MessageLoginReply(user.getString(4));
				}
			}
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
//	public Message loadCustomer (String customerId, String customerType) throws SQLException{
//		String query = null;
//		switch(Integer.parseInt(customerType))
//		//1 - regular, 2 - standard, 3 - full
//		{
//		case 1: {
//			query = "SELECT * FROM customers WHERE id=?;";
//			break;
//		}
//		case 2: {
//			query = "SELECT * FROM std_members WHERE memberID=?;";
//			break;
//		}
//		case 3: {
//			query = "SELECT * FROM full_members WHERE memberID=?;";
//			break;
//		}
//		}
//		PreparedStatement ps = con.prepareStatement(query);
//		ps.setString(1, customerId);
//		ResultSet cst = ps.executeQuery();
//		cst.next();
//		switch(Integer.parseInt(customerType))
//		//1 - regular, 2 - standard, 3 - full
//		{
//		case 1: {
//			Customer stdCust = new Customer(cst.getString(1), cst.getString(2), cst.getString(3), cst.getString(4), cst.getString(5));
//			return new MessageLoginReply(stdCust);
//		}
//		case 2: {
//			STDMember stdMem = new STDMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(10), cst.getInt(8), cst.getString(9));
//			return new MessageLoginReply(stdMem);
//		}
//		case 3: {
//			FullMember fullMem = new FullMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(8));
//			return new MessageLoginReply(fullMem);
//		}
//		}
//		cst.close();
//		ps.close();
//		return null;
//
//	}
	
	public Message loadWorker(String workID, String type) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM workers WHERE id=?");
		ps.setString(1, workID);
		ResultSet wkr = ps.executeQuery();
		wkr.next();
		switch(Integer.parseInt(type))
		{
		case 1: {
			CeoWorker cw = new CeoWorker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 2: {
			ParkingLotManager plm = new ParkingLotManager(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 3: {
			Worker wrk = new Worker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 4: {
			CustomerService cs = new CustomerService(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		}
		wkr.close();
		ps.close();
		return null;
	}

}
