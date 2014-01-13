package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Gal
 *This class is responsible for converting the combo box choice into string.
 *mp contains a hash of the name of the parking lot and the number of them
 */
public class MessageGetParkingLotsID extends Message {

	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglots");
			ResultSet rs = ps.executeQuery();
			Map<String,Integer> mp = new HashMap<String,Integer>();
			while(rs.next())
			{
				mp.put(rs.getString(2), rs.getInt(1));
			}
			ps.close();
			return new MessageGetParkingLotsIDReply(mp);
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
	

	
	

}
