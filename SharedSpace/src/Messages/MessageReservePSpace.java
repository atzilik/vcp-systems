package Messages;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageReservePSpace extends Message {
	private String carNum;
	private int parkingLotID;
	private int floor;
	private int row;
	private int depth;

	

	public MessageReservePSpace(int parkingLotID, int floor,int row, int depth,String carNum) {
		this.carNum = carNum;
		this.parkingLotID = parkingLotID;
		this.floor = floor;
		this.row = row;
		this.depth = depth;
	}



	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = this.sqlConnection.getConnection();
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO parkinglot_map (parkingLotID,floor,row,depth,reserved,carNum) VALUES(?,?,?,?,?,?);");
			ps.setInt(1, parkingLotID);
			ps.setInt(2, floor);
			ps.setInt(3, row);
			ps.setInt(4, depth);
			ps.setBoolean(5,true);
			ps.setString(6, carNum);
			
			ps.executeUpdate();
			return new MessageReservePSpaceReply();
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

}
