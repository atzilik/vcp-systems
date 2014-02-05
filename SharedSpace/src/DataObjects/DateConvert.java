package DataObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.sql.Date;
import java.sql.Time;
/**
 * used to do operations on a several dates which help to compare
 * @author Gal
 *
 */
public class DateConvert {

/**
 * 
 * @param date a sql date
 * @return a new java.util.Date which can be compared properly
 */
	public static java.util.Date fixDate(java.sql.Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}


	/**
	 * get the current date
	 * @return SQL Date
	 */
	public static Date getCurrentSqlDate(){
		return new Date(new java.util.Date().getTime());
	}

	/**
	 * get the current time
	 * @return SQL Time
	 */
	public static Time getCurrentSqlTime(){
		return new Time(new java.util.Date().getTime());
	}

	/**
	 * add days to the given date
	 * @param date SQL Date
	 * @param amount amount of days to add
	 * @return new Date with the added days
	 */
	public static Date addDays(Date date, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, amount);
		return new Date(cal.getTimeInMillis());
	}
	
	public static Date addMinutes(Date date, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, amount);
		return new Date(cal.getTimeInMillis());
	}

	
	public static Time addMinutes(Time time, int amount){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.add(Calendar.MINUTE, amount);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Time(cal.getTimeInMillis());
	}

	/**
	 * 
	 * @param time	given time to manipulate
	 * @param hour 	return hour
	 * @param min	return min
	 * @param sec	return sec
	 * @return return either hour/min/sec of the given time as int
	 */
	public static int getTimeFromSql(Time time, boolean hour, boolean min, boolean sec){
		String newtime = time.toString();
		String val = null;
		if (hour == true)
		{
			val = newtime.substring(0, 2);
		}
		else if (min == true)
		{
			val = newtime.substring(3, 5);
		}
		else
		{
			val = newtime.substring(6, 8);
		}

		return Integer.parseInt(val);
	}


	/**
	 * calculate time difference in minutes between 2 dates
	 * @param date1 date1 in SQL format
	 * @param time1 time1 in SQL format
	 * @param date2 date2 in SQL format
	 * @param time2 time2 in SQL format
	 * @return the difference in minutes between the 2 given dates
	 */
	public static long timeDifference(Date date1, Time time1, Date date2, Time time2)
	{
		java.util.Date fullDate1 = buildFullDate(date1, time1);
		java.util.Date fullDate2 = buildFullDate(date2, time2);
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(fullDate1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(fullDate2);
		return TimeUnit.MILLISECONDS.toMinutes(cal1.getTimeInMillis() - cal2.getTimeInMillis());
	}

	public static long timeDifference(java.util.Date date1, java.util.Date date2)
	{
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date1);

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(date2);
		return TimeUnit.MILLISECONDS.toMinutes(cal1.getTimeInMillis() - cal2.getTimeInMillis());
	}
	
	public static long timeDifference(java.sql.Date date1, java.sql.Date date2)
	{
		
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(DateConvert.fixDate(date1));

		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(DateConvert.fixDate(date2));
		return TimeUnit.MILLISECONDS.toMinutes(cal1.getTimeInMillis() - cal2.getTimeInMillis());
	}

	/**
	 * build up a full date that includes hours and minutes only
	 * @param date date in SQL format
	 * @param time time in SQL format
	 * @return new java.util.Date which holds the date + hours and minutes
	 */
	public static java.util.Date buildFullDate(Date date, Time time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, DateConvert.getTimeFromSql(time, true, false, false));
		cal.set(Calendar.MINUTE, DateConvert.getTimeFromSql(time, false, true, false));
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * convert from a date type String to a date type SQL
	 * @param date String which hold a SQL date
	 * @return SQL type date
	 */
	public static Date stringToSQLDate(String date)
	{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date newDate = null;
		try {
			newDate = sdf1.parse(date);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return new Date(newDate.getTime());  

	}
	/**
	 * convert from a time type String to a time type SQL
	 * @param time String which hold a SQL time 
	 * @return SQL type time
	 */
	public static Time stringToSQLTime(String time)
	{
		return java.sql.Time.valueOf(time);
	}

}
