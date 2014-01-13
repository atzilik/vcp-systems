//package Messages;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class MessageCheckFD extends Message{
//	private String pl;
//	
//	public MessageCheckFD(String pl) {
//		this.pl = pl;
//	}
//
//	@Override
//	public Message doAction() {
//		con = this.sqlConnection.getConnection();
//		try{
//			ResultSet rs = findPl();
//			if(rs.getBoolean(3)==false)
//				return new MessageCheckFDReply(1);  // not active
//			else if(rs.getBoolean(4)==true)
//				return new MessageCheckFDReply(2);  // full
//			else
//				return new MessageCheckFDReply(0);  // good
//		}catch (SQLException e) {e.printStackTrace();}
//		return null;
//	} // doAction
//		
//	
//	public ResultSet findPl() throws SQLException {
//		PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglots where parkingLotID=?;");
//		ps.setString(1, pl);
//		ResultSet rs = ps.executeQuery();
//		if (rs.next())
//		{
//			return rs;
//		}	
//		return null;
//	}
//}
