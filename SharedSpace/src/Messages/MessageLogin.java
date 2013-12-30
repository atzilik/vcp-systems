package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DataObjects.*;




public class MessageLogin extends Message {

	private String userName;
	private String password;
	private Customer cust;
	private Worker wrk;
	
	public MessageLogin(){
		cust = null;
		wrk = null;
	}
	
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
//					loadWorker(user.getString(3), user.getString(5));
				}
				else
					//customer
				{
					return loadCustomer(user.getString(4), user.getString(5));
				}
			}
			user.close();
		} catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public Message loadCustomer (String customerId, String customerType) throws SQLException{
		String tableName = null;
		switch(Integer.parseInt(customerType))
		//1 - regular, 2 - standard, 3 - full
		{
		case 1: tableName = "customers";break;
		case 2: tableName = "std_members";break;
		case 3: tableName = "full_members";break;
		}
		String query = "SELECT * FROM $tableName WHERE id=?;";
		PreparedStatement ps = con.prepareStatement(query.replace("$tableName", tableName));
		ps.setString(1, customerId);
		ResultSet cst = ps.executeQuery();
		cst.next();
		switch(Integer.parseInt(customerType))
		//1 - regular, 2 - standard, 3 - full
		{
		case 1: {
			cust = new STDCustomer(cst.getString(1), cst.getString(2), cst.getString(3), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(7));
			return this;
		}
		case 2: {
			cust = new STDMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(10), cst.getInt(8), cst.getString(9));
			return this;
		}
		case 3: {
			cust = new FullMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(8));
			return this;
		}
		}
		cst.close();
		ps.close();
		return null;

	}
	
	public Message loadWorker(String workID, String type) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM workers WHERE id=?");
		ps.setString(1, workID);
		ResultSet wkr = ps.executeQuery();
		wkr.next();
		switch(Integer.parseInt(type))
		{
		case 1: {
			wrk = new CeoWorker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 2: {
			wrk = new ParkingLotManager(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 3: {
			wrk = new Worker(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		case 4: {
			wrk = new CustomerService(wkr.getString(1), wkr.getString(2), wkr.getString(3), wkr.getString(4), wkr.getInt(5));
			return this;
		}
		}
		wkr.close();
		ps.close();
		return null;

	}
	
	public boolean isEmpty(){
		if (cust == null && wrk == null)
			return true;
		return false;
	}

}
