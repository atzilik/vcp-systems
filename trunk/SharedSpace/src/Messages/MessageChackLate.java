package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import DataObjects.DateConvert;

/**
 * 
 * @author Boaz
 *This class is responsible for the check 10 min late of customers
 *and to send them a mail about this.
 */
public class MessageChackLate extends Message{

	@Override
	public Message doAction() {
		con = this.sqlConnection.getConnection();			
		try{
			ResultSet rs = findLate(); // find late of in check in 

		}catch (SQLException e) {e.printStackTrace();}
		return null;
	} // do action
	
	/**
	 * 
	 * @return result of all of the late customers 
	 * @throws SQLException
	 */

	public ResultSet findLate() throws SQLException {
		try
		{
			// all of the res that are from now or was in the early hour of this date
			PreparedStatement psr = con.prepareStatement("SELECT * FROM reservations where estCinDate=? and estCinHour=? or estCinDate=? and estCinHour<?;");
			psr.setDate(1, DateConvert.getCurrentSqlDate());
			psr.setTime(2, DateConvert.getCurrentSqlTime());
			psr.setDate(3, DateConvert.getCurrentSqlDate());
			psr.setTime(4, DateConvert.getCurrentSqlTime());
			ResultSet rsr = psr.executeQuery();
			if (rsr.next())
			{
				// if this reservation didn't make check in
				PreparedStatement psp = con.prepareStatement("SELECT * FROM parking_control where reservationID=?;");
				do
				{
					psp.setString(1,rsr.getString(1));
					ResultSet rsp = psp.executeQuery();
					if (rsp.isBeforeFirst()==false)  // there is no check in record
					{
						// if it has been over a 10 min late
						if ((DateConvert.timeDifference(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime(), rsr.getDate(5), rsr.getTime(6))==10)||(DateConvert.timeDifference(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime(), rsr.getDate(5), rsr.getTime(6))>10))
						{
							PreparedStatement psc = con.prepareStatement("SELECT * FROM customers where id=? and carID=?;"); 
							psc.setInt(1,rsr.getInt(2));
							psc.setInt(2, rsr.getInt(3));
							ResultSet rsc = psc.executeQuery();
							rsc.next();	
							// prepering and sending the mail
							String subject = "You are been late to your Order";
							String text = "Dear " + rsc.getString(3) + " " + rsc.getString(4) + ".\n"+ "You are late to your order. Do you want to cancel your reservation?";
							String text2 = rsc.getString(5);
							SendMail sendMail = new SendMail();
							sendMail.sendMail(rsc.getString(5), subject, text); 
							
							Random rand = new Random(); 
							int value = rand.nextInt(2); 
//							int value = 1;
							if (value == 0)  // delete the reservation from res table
							{
								PreparedStatement psd = con.prepareStatement("DELETE FROM reservations WHERE reservationId=?");
								psd.setString(1, rsr.getString(1));
								psd.executeUpdate();
								psd.close();
							}
							else  // add fine to the reservation
							{
								PreparedStatement psb = con.prepareStatement("INSERT INTO customer_bill (customerId,carId,date,time,reservationId,sum) VALUES(?,?,?,?,?,?);");
								psb.setInt(1,rsr.getInt(3));
								psb.setInt(2,rsr.getInt(2));
								psb.setDate(3,DateConvert.getCurrentSqlDate());
								psb.setTime(4,DateConvert.getCurrentSqlTime());
								psb.setInt(5,rsr.getInt(1));
								psb.setDouble(6,(rsr.getInt(10)*0.2));
								psb.executeUpdate();
								psb.close();
							}
						}
					}
				}while(rsr.next());
			}
		}catch (SQLException e) {e.printStackTrace();}

		return null;
	}
}



