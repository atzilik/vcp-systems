package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.ParkingLot;

import DataObjects.ParkingSpace;

public class MessageMapParkingLot extends Message {

	private ParkingLot[] parkinglots;
	private int size;
	
	public MessageMapParkingLot(int size){
		this.size = size;
	}
	@Override
	public Message doAction() {
		con = sqlConnection.getConnection();
		try{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM parkinglots;");
			ResultSet rs = ps.executeQuery();
			parkinglots = new ParkingLot[size];
			for (int i = 0; i < size; i++)
			{
				rs.next();
				parkinglots[i] = new ParkingLot(rs.getInt(1),rs.getString(2),rs.getBoolean(3),rs.getBoolean(4),rs.getInt(5),rs.getInt(6));
			}
			ps.close();
			for (int i = 0; i < size; i++)
			{
				ps = con.prepareStatement("SELECT * FROM parkinglot_map where parkingLotID=?;");
				ps.setInt(1, parkinglots[i].getParkingLotID());
				rs = ps.executeQuery();
				ParkingSpace[][][] pspace = parkinglots[i].getParkingspace();
				while(rs.next())
				{
					//reserved parking space
					if (rs.getBoolean(5))
					{
						pspace[rs.getInt(2)][rs.getInt(3)][rs.getInt(4)] = new ParkingSpace(rs.getInt(2),rs.getInt(3),rs.getInt(4),false,true,false,0, rs.getDate(8),rs.getTime(9));
					}
					//disabled parking space
					else if(rs.getBoolean(6))
					{
						pspace[rs.getInt(2)][rs.getInt(3)][rs.getInt(4)] = new ParkingSpace(rs.getInt(2),rs.getInt(3),rs.getInt(4),true,false,false,0, rs.getDate(8),rs.getTime(9));
					}
					else
					{
						pspace[rs.getInt(2)][rs.getInt(3)][rs.getInt(4)] = new ParkingSpace(rs.getInt(2),rs.getInt(3),rs.getInt(4),false,false,true,rs.getInt(7), rs.getDate(8),rs.getTime(9));
					}
					
				}
				for (int j = 0; j < ParkingLot.FLOORS_SIZE; j++)
				{
					for (int k = 0; k < ParkingLot.ROWS_SIZE; k++)
					{
						for (int m = 0; m < parkinglots[i].getDepthSize(); m++)
						{
							if (pspace[j][k][m] == null)
							{
								pspace[j][k][m] = new ParkingSpace(j,k,m);
							}
						}
					}
				}
				ps.close();
				parkinglots[i].setParkingspace(pspace);
				parkinglots[i].setRobot();
			}
			return new MessageMapParkingLotReply(parkinglots);
			
		}catch (SQLException e) {e.printStackTrace();}
		// TODO Auto-generated method stub
		return null;
	}

}
