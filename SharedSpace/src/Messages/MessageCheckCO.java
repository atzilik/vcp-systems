package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.DateConvert;
import DataObjects.Reservation;

/**
 * 
 * @author Boaz
 *This class is responsible for the check out checking .
 */
public class MessageCheckCO extends Message{
	private String carNum;
	private String id;
	
	/**
	 * 
	 * @param id id of the customer
	 * @param carNum car number of the customer
	 */
	
	public MessageCheckCO(String id, String carNum) {
		this.carNum = carNum;
		this.id = id;
	}

	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findPl(); // check id carNum and date of today 
			if (rs == null)
				return new MessageCheckCOReply(0);  // no such record on parking control
			else
			{
				do
				{
						return new MessageCheckCOReply(1); //  no check out
				}
				while (rs.next());
			}

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	} // doAction
	
	/**
	 * 
	 * @return result of the customer that has been checked in in this date 
	 * @throws SQLException
	 */
	
	public ResultSet findPl() throws SQLException {
		PreparedStatement ps = con.prepareStatement("SELECT * FROM parking_control where customerId=? and carNum=? and realCotDate is NULL and realCotHour is NULL;");
		ps.setString(1, id);
		ps.setString(2, carNum);
		ResultSet rs = ps.executeQuery();
		
		if (rs.next())
		{
			return rs;
		}	
		return null;
	}

}
