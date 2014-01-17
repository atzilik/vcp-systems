package Messages;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.corba.se.spi.orb.DataCollector;

import DataObjects.DateConvert;

public class MessageWeeklyStatistics extends Message {
	private Map<String, Integer> map;

	public MessageWeeklyStatistics(Map<String, Integer> map){
		this.map = map;
	}
	@Override
	public Message doAction() {
		// TODO Auto-generated method stub
		con = sqlConnection.getConnection();
		try{
			Set keys = map.keySet();
			for (Iterator i = keys.iterator(); i.hasNext();)
			{
				String parkinglot = (String)i.next();
				buildWeeklyStatistics(map.get(parkinglot));
			}
//			PreparedStatement ps = con.prepareStatement("SELECT * FROM daily_statistic_data WHERE date=? and parkingLotID=?;");
//			//building the date
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
//			cal.add(Calendar.DAY_OF_MONTH, -7);
//			ps.setDate(1, new Date(cal.getTimeInMillis()));
//			ResultSet rs = ps.executeQuery();
//			while(rs.next())
//			{
//
//			}
		}catch (SQLException e) {e.printStackTrace();}
		return null;
	}

	public void buildWeeklyStatistics(int parkingLotID) throws SQLException{
		PreparedStatement ps = con.prepareStatement("SELECT * FROM vcp.daily_statistic_data WHERE date between ? and ? and parkingLotID=?;");
		//building the date
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateConvert.buildFullDate(DateConvert.getCurrentSqlDate(), DateConvert.getCurrentSqlTime()));
		cal.add(Calendar.DAY_OF_MONTH, -7);
		ps.setDate(1, new Date(cal.getTimeInMillis()));
		ps.setDate(2, DateConvert.getCurrentSqlDate());
		ps.setInt(3, parkingLotID);
		ResultSet rs = ps.executeQuery();
		int[][] weeklyStatisticData = new int[7][7];
		int count = 0;
		
		//building the current parking lot statistic
		for (int i = 0; i < weeklyStatisticData.length; i++)
		{
			while (rs.next())
			{
				
				for (int j = 0;j < weeklyStatisticData[0].length; j++)
				{
					weeklyStatisticData[i][j] = rs.getInt(j + 3);
				}
				count++;
			}
		}
		//identify if parking lot doesn't have a full 7 days data
		if (count < 7)
		{
			for (int i = count; i < weeklyStatisticData.length; i++)
			{
				for (int j = 0; j < weeklyStatisticData[0].length; j++)
				{
					weeklyStatisticData[i][j] = -1;
				}
			}
		}
		updateDB(parkingLotID, calculateWeeklyAverage(weeklyStatisticData), calculateWeeklyMedian(weeklyStatisticData), calculateWeeklyFreq(weeklyStatisticData));

	}

	public double[] calculateWeeklyAverage(int[][] weeklyData){
		double[] avg = new double[weeklyData[0].length];
		int i, j, daysCount;
		for (i = 0; i < weeklyData.length; i++)
		{
			int sum = 0;
			daysCount = 0;
			for (j = 0; j < weeklyData[0].length; j++)
			{
				if (weeklyData[j][i] != -1)
				{
					sum += weeklyData[j][i];
					daysCount++;
				}	
			}
			avg[j-1] = sum / daysCount;
		}
		return avg;
	}
	
	public int[] calculateWeeklyMedian(int[][] weeklyData){
		int i,j;
		int[] median = new int[7];
//		Integer[] med = new Integer[7];
//		List<Integer> list = new ArrayList<Integer>();
//		for (i = 0; i < weeklyData.length; i++)
//		{
//			list.removeAll(list);
//			for (j = 0; j < weeklyData[0].length; j++)
//			{
//				if (weeklyData[i][j] != -1)
//				{
//					list.add(weeklyData[j][i]);
//				}
//			}
//			med = (Integer[]) list.toArray();
//			Arrays.sort(med);
//			median[j-1] = med[med.length / 2];
//		}
		return median;
	}
	
	public double[] calculateWeeklyFreq(int[][] weeklyData){
		double[] freq = new double[7];
		return freq;
	}
	
	public void updateDB(int parkingLotID, double[] avg, int[] med, double[] freq) throws SQLException{
		PreparedStatement ps = con.prepareStatement("INSERT INTO weekly_statistic_data (date,parkingLotID,avg_reservations_used,med_reservations_used,freq_reservations_used,avg_reservations_canceled,med_reservations_canceled,freq_reservations_canceled,avg_STDmembers_amount,med_STDmembers_amount,freq_STDmembers_amount,avg_fullMember_amount,med_fullMember_amount,,freq_fullMember_amount,avg_STDmembers_with_cars,med_STDmembers_with_cars,freq_STDmembers_with_cars,avg_fullMember_with_cars,med_fullMember_with_cars,freq_fullMember_with_cars,avg_parking_delays,med_parking_delays,freq_parking_delays) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
		ps.setDate(1, DateConvert.getCurrentSqlDate());
		ps.setInt(2, parkingLotID);
		int j = 3;
		for (int i = 0; i < avg.length; i++)
		{
			ps.setDouble(j++, avg[i]);
			ps.setDouble(j++, med[i]);
			ps.setDouble(j++, freq[i]);
		}
		ps.executeUpdate();
		ps.close();
	}
}
