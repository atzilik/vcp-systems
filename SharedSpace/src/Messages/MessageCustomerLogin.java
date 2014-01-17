package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.FullMember;
import DataObjects.STDCustomer;
import DataObjects.STDMember;

/**
 * check login details of a customer
 * @author Gal
 *
 */
public class MessageCustomerLogin extends Message {
	private String id;
	private String carNum;
	private int type;
	private PreparedStatement ps;
	
	public MessageCustomerLogin(String id, String carNum) {
		this.id = id;
		this.carNum = carNum;
	}
	@Override
	/**
	 * checking customer login details and create a matching instance of it with all the customer details from DB.
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			ResultSet rs = findCustomer();
			switch (type)
			{
			case 1: {
				MessageCustomerLoginReply clr = new MessageCustomerLoginReply(new STDCustomer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
				ps.close();
				return clr;
			}
			case 2: {
				MessageCustomerLoginReply clr = new MessageCustomerLoginReply(new STDMember(rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(1), rs.getDate(7), rs.getInt(8), rs.getTime(9))); 
				ps.close();
				return clr;
			}
			case 3: {
				MessageCustomerLoginReply clr = new MessageCustomerLoginReply(new FullMember(rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(1), rs.getDate(7)));
				ps.close();
				return clr;
			}
			default: {
				ps.close();
				return new MessageCustomerLoginReply();		
			}
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public ResultSet findCustomer() throws SQLException{
		ps = con.prepareStatement("SELECT * FROM customers WHERE id=? and carID=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next())
		{
			type = 1;
			return rs;
		}
		ps.close();
		ps = con.prepareStatement("SELECT * FROM members WHERE memberID=? and carID=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		rs = ps.executeQuery();
		if (rs.next())
		{
			type = rs.getInt(10);
			return rs;
		}	
		return null;
	}

}