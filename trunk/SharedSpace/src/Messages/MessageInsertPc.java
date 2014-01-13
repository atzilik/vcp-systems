package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;

import DataObjects.Customer;
import DataObjects.DateConvert;
import DataObjects.FullMember;
import DataObjects.Reservation;
import DataObjects.STDMember;

/**
 * 
 * @author Boaz
 *This class is responsible for insert the reservation into the parking_lot table.
 */
public class MessageInsertPc extends Message{
	Reservation res = null;
	Customer mem = null;
	Date todayDate = DateConvert.getCurrentSqlDate(); 
	Time currTime = DateConvert.getCurrentSqlTime();
	private boolean late;
	private int pl;
	
	/**
	 * 
	 * @param res reservation details
	 * @param late late or not
	 */
	public MessageInsertPc(Reservation res, boolean late){
		this.res = res;	
		this.late = late;
	}

	/**
	 * 
	 * @param mem member details
	 */
	public MessageInsertPc(Customer mem){
		this.mem = mem;	
	}

	/**
	 * 
	 * @param mem member details
	 * @param pl number of the parkinglot
	 */
	public MessageInsertPc(Customer mem,int pl){
		this.mem = mem;	
		this.pl = pl;	}
	
	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();
		// insert the detailes into the parking_control table
		try {
			if (res != null)  // there is a reservation => std customer
			{
				PreparedStatement ps = con.prepareStatement("INSERT INTO parking_control (customerID,carNum,parkingLotID,cinDate,cinHour,cotDate,cotHour,late,reservationID) VALUES(?,?,?,?,?,?,?,?,?);");
				ps.setString(1,res.getCid());
				ps.setInt(2,res.getCarId());
				ps.setString(3,res.getPl());
				ps.setDate(4,res.getEstCinDate());
				ps.setTime(5,res.getEstCinHour());
				ps.setDate(6,res.getEstCoutDate());
				ps.setTime(7,res.getEstCoutHour());
				ps.setBoolean(8, late);
				ps.setString(9,res.getRid());
				ps.executeUpdate();
				ps.close();

				return new MessageInsertPcReply(res);
			}
			else if (mem instanceof STDMember)
			{
				PreparedStatement ps = con.prepareStatement("INSERT INTO parking_control (customerID,carNum,parkingLotID,cinDate,cinHour,cotDate,cotHour) VALUES(?,?,?,?,?,?,?);");
				ps.setString(1,((STDMember) mem).getMemberId());
				ps.setString(2,mem.getCarId());
				ps.setInt(3,((STDMember) mem).getParkingLotId());
				ps.setDate(4,todayDate);
				ps.setTime(5,currTime);
				ps.setDate(6,todayDate);
				ps.setTime(7,((STDMember) mem).getStdCheckOut());

				ps.executeUpdate();
				ps.close();

				return new MessageInsertPcReply(mem);

			}
			else if (mem instanceof FullMember)
			{
				PreparedStatement ps = con.prepareStatement("INSERT INTO parking_control (customerID,carNum,parkingLotID,cinDate,cinHour,cotDate,cotHour) VALUES(?,?,?,?,?,?,?);");
				ps.setString(1,((FullMember) mem).getMemberId());
				ps.setString(2,mem.getCarId());
				ps.setInt(3,pl);
				ps.setDate(4,todayDate);
				ps.setTime(5,currTime);
				ps.setDate(6,todayDate); // to change to +14 days
				ps.setTime(7,currTime);

				ps.executeUpdate();
				ps.close();

				return new MessageInsertPcReply(mem);
			}

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}
}
