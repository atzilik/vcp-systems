package Messages;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;

import DataObjects.FullComplaint;
/**
 * handle getting details for activity report
 * @author omri
 *
 */
public class MessageGetDetailsForActivityReport extends Message {


	private Connection sqlConn;
	
/**
 * number of days for report
 */
	private int NumOfDays;
	/**
	 * start report date
	 */
	private Date from;
	/**
	 * index for column from DB
	 */
	private int index;
	
	public MessageGetDetailsForActivityReport(int numOfDays, Date from, int index) {
		this.NumOfDays = numOfDays;
		this.from=from;
		this.index = index;
	}
	
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		
		sqlConn = this.sqlConnection.getConnection();
		int days[] = new int[NumOfDays];
		int freq[] = new int[10];
		int numOfParkingLots = 6;
		int sumInDay = 0;
		int total = 0;
		int avg = 0;
		
		try{
			PreparedStatement ps = sqlConn.prepareStatement("SELECT * FROM daily_statistic_data");
			ResultSet rs = ps.executeQuery();
			
			int row = countRows(rs);
			
			if(row/6 >= NumOfDays)
			{
				rs = ps.executeQuery();
				
				rs.next();
				
				while(rs.getDate(1).compareTo(from) != 0)
					rs.next();
				
				row = countRows(rs) + 1;
				
				if(row/6 >= NumOfDays)
				{
					rs = ps.executeQuery();
					
					rs.next();
					
					while(rs.getDate(1).compareTo(from) != 0)
						rs.next();
					
					for(int j=0 ; j<NumOfDays ; j++)
					{
						for(int i=0 ; i<numOfParkingLots ; i++)
						{
							sumInDay += rs.getInt(index);
							rs.next();
						}
						
						total += sumInDay;
						days[j] = sumInDay;
						if(sumInDay>100)
							freq[9]++;
						else if(sumInDay==0)
							freq[0]++;
						else
							freq[(sumInDay-1)/10]++;
						
						sumInDay=0;
					}
						
						for(int i=0 ; i < NumOfDays ; i++)
							avg+=days[i];
						avg=avg/NumOfDays;
						
						Arrays.sort(days);
					
					return new MessageGetDetailsForActivityReportReply(total,freq,avg,0,true);
				}
				else
				{
					JOptionPane.showMessageDialog(null, "No details for this range of date");
					return new MessageGetDetailsForActivityReportReply(total,freq,avg,0,false);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "No details for this range of date");
				return new MessageGetDetailsForActivityReportReply(total,freq,avg,0,false);
			}
				
		}catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error");
			return null;
		}
	}
	/**
	 * count number of rows in table
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
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
