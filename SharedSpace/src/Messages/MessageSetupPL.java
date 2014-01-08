package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.ParkingLot;


public class MessageSetupPL extends Message {
	private int parkingLotID;
	private int depthSize;
	private boolean updateDepthSize;
	
	public MessageSetupPL(int parkingLotID){
		this.parkingLotID = parkingLotID;
		updateDepthSize = false;
	}
	
	public MessageSetupPL(int parkingLotID, int depthSize){
		this.parkingLotID = parkingLotID;
		this.depthSize = depthSize;
		updateDepthSize = true;
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
			String[] plTable = new String[6];
			for (int i = 0 ; i < plTable.length ; i++)
			{
				plTable[i] = rs.getString(i+1);
			}
			ps.close();
			//check if its active already
			if (plTable[2].equals("1"))
			{
				return new MessageSetupPLReply();
			}
				
			if (updateDepthSize)
			{
				ps = con.prepareStatement("UPDATE parkinglots SET active=1,depthSize=? where parkingLotID=?;");
				ps.setInt(1, depthSize);
				ps.setInt(2, parkingLotID);
				ps.executeUpdate();
				ps.close();
				return new MessageSetupPLReply(parkingLotID,depthSize);
			}
			else
			{
				ps = con.prepareStatement("UPDATE parkinglots SET active=1 where parkingLotID=?;");
				ps.setInt(1, parkingLotID);
				ps.executeUpdate();
				ps.close();
				return new MessageSetupPLReply(parkingLotID);
			}
		}catch (SQLException e) {};
		return null;
	}
	
	

}
