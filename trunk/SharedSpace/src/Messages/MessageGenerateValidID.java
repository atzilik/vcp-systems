package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 * Generate an id and check if its valid.
 * @author Gal
 *
 */
public class MessageGenerateValidID extends Message {
	/**
	 * type = 1 generate memberID, type = 2 generate reservationID
	 */
	private int type;

	public MessageGenerateValidID(int type){
		this.type = type;
	}

	@Override
	/**
	 * generate a random id, check if it doesn't exist and return it.
	 */
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try{
			if (type == 1)
			{
				return generateMemberID();
			}
			else 
			{
				return generateReservationID();
			}
			

		}catch (SQLException e) {e.printStackTrace();};
		return null;
	}

	public Message generateMemberID() throws SQLException{
		while(true)
		{
			String memberID = Integer.toString(100000 + new Random().nextInt(900000));
			PreparedStatement ps = con.prepareStatement("SELECT * FROM members WHERE memberID=?;");
			ps.setString(1, memberID);
			ResultSet rs = ps.executeQuery();
			if (rs.isBeforeFirst() == false)
			{
				ps.close();
				return new MessageGenerateValidIDReply(memberID);
			}
			ps.close();
		}
	}

	public Message generateReservationID() throws SQLException{
		while(true)
		{
			String reservationID = Integer.toString(100000 + new Random().nextInt(900000));
			PreparedStatement ps = con.prepareStatement("SELECT * FROM reservations WHERE reservationId=?;");
			ps.setString(1, reservationID);
			PreparedStatement ps1 = con.prepareStatement("SELECT * FROM cancel_reservation WHERE reservationId=?;");
			ps1.setString(1, reservationID);
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();
			if (rs.isBeforeFirst() == false && rs1.isBeforeFirst() == false)
			{
				ps.close();
				return new MessageGenerateValidIDReply(reservationID);
			}
			ps.close();
		}
	}
}
