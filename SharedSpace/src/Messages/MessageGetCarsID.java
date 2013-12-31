package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MessageGetCarsID extends Message {
	private String customerID;
	
	public MessageGetCarsID(String customerID){
		this.customerID = customerID;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM customers_cars WHERE customerID=?;");
			ps.setString(1, customerID);
			ResultSet rs = ps.executeQuery();
			List<String> ls = new ArrayList<String>();
			while (rs.next())
			{
				ls.add(rs.getString(2));
			}
			return new MessageGetCarsIDReply(ls);
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
