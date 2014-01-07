package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.ParkingLot;


public class MessageSetupPL extends Message {
	private int parkingLotID;
	
	public MessageSetupPL(int parkingLotID){
		this.parkingLotID = parkingLotID;
		
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglots WHERE parkingLotID=?;");
			ps.setInt(1, parkingLotID);
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			String[] arr = new String[6];
			for (int i = 0 ; i < arr.length ; i++)
				arr[i] = rs.getString(i+1);
			if (rs.getInt(3) == 0)
			{
				ps.close();
				ps = con.prepareStatement("UPDATE parkinglots SET active=1 where parkingLotID=?;");
				ps.setInt(1, parkingLotID);
				ps.executeUpdate();
				ps.close();
			}
//			int freespace = ParkingLot.FLOORS_SIZE * ParkingLot.ROWS_SIZE * rs.getInt(5);
//			switch(parkingLotID){
//			case 111: {
//				TelAvivPL tlvPL = new TelAvivPL(parkingLotID, rs.getString(2), true, false, null, freespace,rs.getInt(5));
//				MessageSetupPLReply splr = new MessageSetupPLReply(tlvPL);
//			}
//			}
			
			ps.close();
//			return splr;
		}catch (SQLException e) {};
		return null;
	}

}
