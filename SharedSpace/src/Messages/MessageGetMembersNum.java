package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DataObjects.DisabledParkingSpace;

public class MessageGetMembersNum extends Message {
	
	private Connection sqlConn;

	@Override
	public Message doAction() {
		sqlConn = this.sqlConnection.getConnection();
		
		try{
            PreparedStatement ps = sqlConn.prepareStatement("SELECT memberID, COUNT(*) FROM members GROUP BY memberID HAVING COUNT(*) > 1;");
            ResultSet rs = ps.executeQuery();
            int numOfMembersWithManyCars = countRows(rs);
            
            ps = sqlConn.prepareStatement("SELECT * FROM members");
            rs = ps.executeQuery();
            int numOfMembers = countRows(rs);
            
            ps.close();
			return new MessageGetMembersNumReply(numOfMembersWithManyCars,numOfMembers);
			
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}
	}
	
    public int countRows(ResultSet rs) throws SQLException
    {
            int count = 0;
            while(rs.next())
            {
                    count++;
            }
            return count;
    }

}
