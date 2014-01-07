package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.ParkingLot;
import DataObjects.ParkingSpace;

public class MessageUpdatePLMap extends Message {
	private int parkingLotID;
	private ParkingSpace[][][] parkingSpace;
	
	
	public MessageUpdatePLMap(int parkingLotID, ParkingSpace[][][] parkingSpace){
		this.parkingLotID = parkingLotID;
		this.parkingSpace = parkingSpace;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{

			for (int i = 0; i < parkingSpace.length; i++)
			{
				for (int j = 0; j < parkingSpace[0].length; j++)
				{
					for (int k = 0; k < parkingSpace[0][0].length; k++)
					{
						PreparedStatement ps = con.prepareStatement("SELECT * FROM vcp.parkinglot_map WHERE parkingLotID=? and floor=? and row=? and depth=?;");
						ps.setInt(1, parkingLotID);
						ps.setInt(2, parkingSpace[i][j][k].getFloor());
						ps.setInt(3, parkingSpace[i][j][k].getRow());
						ps.setInt(4, parkingSpace[i][j][k].getDepth());
						ResultSet rs = ps.executeQuery();
						
						//if this parking space already exist
						if (rs.isBeforeFirst())
						{
							PreparedStatement ps1;
							//if an update to db is needed
							if (parkingSpace[i][j][k].isAvailable() == false)
							{
								ps1 = con.prepareStatement("UPDATE parkinglot_map SET reserved=?,disabled=?,carNum=?,cotDate=?,cotHour=? where parkingLotID=? and floor=? and row=? and depth=?;");
								ps1.setBoolean(1, parkingSpace[i][j][k].isReserved());
								ps1.setBoolean(2, parkingSpace[i][j][k].isDisabled());
								ps1.setInt(3, parkingSpace[i][j][k].getCarNum());
								ps1.setDate(4, parkingSpace[i][j][k].getCheckOutdate());
								ps1.setTime(5, parkingSpace[i][j][k].getCheckOutTime());
								ps1.setInt(6, parkingLotID);
								ps1.setInt(7, parkingSpace[i][j][k].getFloor());
								ps1.setInt(8, parkingSpace[i][j][k].getRow());
								ps1.setInt(9, parkingSpace[i][j][k].getDepth());
							}
							//delete if not needed anymore
							else
							{
								ps1 = con.prepareStatement("DELETE FROM parkinglot_map WHERE parkingLotID=? and floor=? and row=? and depth=?;");
								ps1.setInt(1, parkingLotID);
								ps1.setInt(2, parkingSpace[i][j][k].getFloor());
								ps1.setInt(3, parkingSpace[i][j][k].getRow());
								ps1.setInt(4, parkingSpace[i][j][k].getDepth());
							}
							ps1.executeUpdate();
							ps1.close();
						}
						//if it doesn't exist then check if it need to be added
						else
						{
							if (parkingSpace[i][j][k].isAvailable() == false)
							{
								PreparedStatement ps1 = con.prepareStatement("INSERT INTO parkinglot_map (parkingLotID,floor,row,depth,reserved,disabled,carNum,cotDate,cotHour) VALUES (?,?,?,?,?,?,?,?,?);");
								ps1.setInt(1, parkingLotID);
								ps1.setInt(2, parkingSpace[i][j][k].getFloor());
								ps1.setInt(3, parkingSpace[i][j][k].getRow());
								ps1.setInt(4, parkingSpace[i][j][k].getDepth());
								ps1.setBoolean(5, parkingSpace[i][j][k].isReserved());
								ps1.setBoolean(6, parkingSpace[i][j][k].isDisabled());
								ps1.setInt(7, parkingSpace[i][j][k].getCarNum());
								ps1.setDate(8, parkingSpace[i][j][k].getCheckOutdate());
								ps1.setTime(9, parkingSpace[i][j][k].getCheckOutTime());
								ps1.executeUpdate();
								ps1.close();
							}
						}
						
					}
				}
			}
			return new MessageEmptyReply();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
