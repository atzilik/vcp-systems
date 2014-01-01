package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.Customer;
import DataObjects.FullMember;
import DataObjects.STDMember;

public class MessageCustomerLogin extends Message {
	private String id;
	private String carNum;
	private int type;
	
	public MessageCustomerLogin(String id, String carNum) {
		this.id = id;
		this.carNum = carNum;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			ResultSet rs = findCustomer();
			switch (type)
			{
			case 0: return new MessageCustomerLoginReply();
			case 1: return new MessageCustomerLoginReply(new Customer(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			case 2: return new MessageCustomerLoginReply(new STDMember(rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(1), rs.getString(7), rs.getBoolean(10), rs.getInt(8), rs.getString(9)));
			case 3: return new MessageCustomerLoginReply(new FullMember(rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(1), rs.getString(7), rs.getBoolean(8)));
			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	
	public ResultSet findCustomer() throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM customers WHERE id=? and carID=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next())
		{
			type = 1;
			return rs;
		}
		
		ps = con.prepareStatement("SELECT * FROM std_members WHERE memberID=? and carID=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		rs = ps.executeQuery();
		
		if (rs.next())
		{
			type = 2;
			return rs;
		}
			
		
		ps = con.prepareStatement("SELECT * FROM full_members WHERE memberID=? and carID=?;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		rs = ps.executeQuery();
		
		if (rs.next())
		{
			type = 3;
			return rs;
		}
		type = 0;
		return null;
	}

}