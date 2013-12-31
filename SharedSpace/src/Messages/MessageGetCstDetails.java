package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.Customer;
import DataObjects.FullMember;
import DataObjects.STDMember;

public class MessageGetCstDetails extends Message {
	private String customerID;
	private String carNum;
	private String customerType;
	public MessageGetCstDetails(String customerID, String carNum){
		this.customerID = customerID;
		this.carNum = carNum;
	}

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customers_cars WHERE customerID=? and carID=?;");
			ps.setString(1, customerID);
			ps.setString(2, carNum);
			ResultSet rs = ps.executeQuery();
			rs.next();
			customerType = rs.getString(3);
			String query = null;
			switch(customerType)
			//1 - regular, 2 - standard, 3 - full
			{
			case "1": {
				query = "SELECT * FROM customers WHERE id=?;";
				break;
			}
			case "2": {
				query = "SELECT * FROM std_members WHERE memberID=?;";
				break;
			}
			case "3": {
				query = "SELECT * FROM full_members WHERE memberID=?;";
				break;
			}
			}
			
			PreparedStatement ps1 = con.prepareStatement(query);
			ps1.setString(1, rs.getString(1));
			ResultSet cst = ps1.executeQuery();
			cst.next();
			switch(customerType)
			//1 - regular, 2 - standard, 3 - full
			{
			case "1": {
				Customer stdCust = new Customer(cst.getString(1), cst.getString(2), cst.getString(3), cst.getString(4), cst.getString(5));
				return new MessageGetCstDetailsReply(stdCust);
			}
			case "2": {
				STDMember stdMem = new STDMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(10), cst.getInt(8), cst.getString(9));
				return new MessageGetCstDetailsReply(stdMem);
			}
			case "3": {
				FullMember fullMem = new FullMember(cst.getString(3), cst.getString(2), cst.getString(4), cst.getString(5), cst.getString(6), cst.getString(1), cst.getString(7), cst.getBoolean(8));
				return new MessageGetCstDetailsReply(fullMem);
			}
			}
			rs.close();
			ps.close();
			cst.close();
			ps1.close();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
