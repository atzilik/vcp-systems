package Messages;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataObjects.DateConvert;

/**
 * 
 * @author Boaz
 *This class is responsible for the check 30 min late of customers
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
						// if it has been over a 30 min late
						if ((DateConvert.timeDifference(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime(), rsr.getDate(5), rsr.getTime(6))==30)||(DateConvert.timeDifference(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime(), rsr.getDate(5), rsr.getTime(6))>30))
						{
							PreparedStatement psc = con.prepareStatement("SELECT * FROM customers where id=? and carID=?;"); 
							psc.setInt(1,rsr.getInt(2));
							psc.setInt(2, rsr.getInt(3));
							ResultSet rsc = psc.executeQuery();
							rsc.next();	
							// prepering and sending the mail
							String subject = "You are been late to your Order";
							String text = "Dir " + rsc.getString(3) + " " + rsc.getString(4) + ".\n"+ "You are been late to your order. Do you want to abort your order?";
							String text2 = rsc.getString(5);
							SendMail sendMail = new SendMail();
							sendMail.sendMail(rsc.getString(5), subject, text); 
							// delete the reservation from res table
							PreparedStatement psd = con.prepareStatement("DELETE FROM reservations WHERE reservationId=?");
							psd.setString(1, rsr.getString(1));
							psd.executeUpdate();
							psd.close();
						}
					}
				}while(rsr.next());
			}
		}catch (SQLException e) {e.printStackTrace();}

		return null;
	}
}



