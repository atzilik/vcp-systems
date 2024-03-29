package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * 
 * @author omri
 *This class is responsible for request to change a rate for pl by employer.
 */
public class MessageRequestChangeParkingRate extends Message {
	
	private Connection sqlConn;
	
	private float occasional;
	private float reserved;
	private int parkingLotId;
	private String mngId;
	
	
	//private MessageRequestChangeParkingRateReplay msgCPFreply = new MessageRequestChangeParkingRateReplay();
/**
 * 
 * @param pId parking lot number
 * @param occ rate of occasional customer
 * @param res reserved
 * @param mngId maneger id
 */
	public MessageRequestChangeParkingRate(int pId, float occ, float res, String mngId) {
		parkingLotId = pId;
		occasional = occ;
		reserved = res;
		this.mngId = mngId;
		this.MessageType = "MessageRequestChangeParkingRate";
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("INSERT INTO ratereq (parkingLotId,occasional,reserved,standard,full,mngId) VALUES(?,?,?,?,?,?);");
			ps.setInt(1, parkingLotId);
			ps.setFloat(2, occasional);
			ps.setFloat(3, reserved);
			ps.setFloat(4, reserved*60);
			ps.setFloat(5, reserved*72);
			ps.setString(6, mngId);
			ps.executeUpdate();


			return new MessageRequestChangeParkingRateReply(true);
			
		}catch (SQLException e) {
			if (e.getMessage().contains("Duplicate entry"))
			{
				JOptionPane.showMessageDialog(null, "Request already exists");
			}	
			return new MessageRequestChangeParkingRateReply(false);
		}
	
	}

}
