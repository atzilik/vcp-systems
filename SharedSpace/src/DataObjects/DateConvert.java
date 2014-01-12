package DataObjects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.sql.Date;
import java.sql.Time;

public class DateConvert {

//	/**
//	 * check if 2 given SQL dates are the same
//	 * @param date1 SQL Date
//	 * @param date2	SQL Date
//	 * @return true is dates are the same, false otherwise
//	 */
//	public static boolean equalsDate(Date date1, Date date2){
//		java.util.Date newdate1 = fixDate(date1);
//		java.util.Date newdate2 = fixDate(date2);
//		return newdate1.equals(newdate2);
//	}
//	
//	/**
//	 * check if date1 is after date2
//	 * @param date1 SQL Date
//	 * @param date2	SQL Date
//	 * @return true is date1 is after date2, false otherwise
//	 */
//	public static boolean afterDate(Date date1, Date date2){
//		java.util.Date newdate1 = fixDate(date1);
//		java.util.Date newdate2 = fixDate(date2);
//		return newdate1.after(newdate2);
//	}
//	
//	/**
//	 * check if date 1 is before date 2
//	 * @param date1	SQL Date
//	 * @param date2	SQL Date
//	 * @return true if date1 is before date2, false otherwise
//	 */
//	public static boolean beforeDate(Date date1, Date date2){
//		java.util.Date newdate1 = fixDate(date1);
//		java.util.Date newdate2 = fixDate(date2);
//		return newdate1.before(newdate2);
//	}
//	/**
//	 * 
//	 * @param time1 
//	 * @param time2
//	 * @return  0  - same time
//	 * 			1  - time1 later time
//	 * 		   -1 - time2 later time 
//	 */
//	public static int compareTime(java.sql.Time time1, java.sql.Time time2)
//	{
//		String newtime1 = time1.toString();
//		String newtime2 = time2.toString();
//		String hour1 = newtime1.substring(0, 2);
//		String hour2 = newtime2.substring(0, 2);
//		String minutes1 = newtime1.substring(3, 5);
//		String minutes2 = newtime2.substring(3, 5);
//		int finalHour1 = Integer.parseInt(hour1);
//		int finalHour2 = Integer.parseInt(hour2);
//		int finalMin1 = Integer.parseInt(minutes1);
//		int finalMin2 = Integer.parseInt(minutes2);
//		
//		if (finalHour1 > finalHour2)
//			return 1;
//		if (finalHour1 < finalHour2)
//			return -1;
//		if (finalMin1 > finalMin2)
//			return 1;
//		if (finalMin1 < finalMin2)
//			return -1;
//		return 0;
//	}
	
//	/**
//	 * fix SQL Date so it can be compared
//	 * @param date SQL Date
//	 * @return java.util Date that can be compared
//	 */
	public static java.util.Date fixDate(java.sql.Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
//	
//	/**
//	 * fix SQL Time so it can be compared
//	 * @param time SQL Time
//	 * @return SQL Time that can be compared
//	 */
//	public static java.sql.Time fixTime(java.sql.Time time){
//		Calendar cal = Calendar.getInstance();
//		cal.setTime(time);
//		cal.set(Calendar.MILLISECOND, 0);
//		return new java.sql.Time(cal.getTime().getTime());
//	}
	
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
	
//	/**
//	 * compare 2 sql times
//	 * @param time1
//	 * @param time2
//	 * @return 0 if equals, 1 if time1 after time2, -1 if time1 before time2
//	 */
//	public static int compareTime(Time time1, Time time2){
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTime(time1);
//		cal1.set(Calendar.HOUR_OF_DAY, DateConvert.getTimeFromSql(time1, true, false, false));
//		cal1.set(Calendar.MINUTE, DateConvert.getTimeFromSql(time1, false, true, false));
//		cal1.set(Calendar.SECOND, 0);
//		cal1.set(Calendar.MILLISECOND, 0);
//		
//		Calendar cal2 = Calendar.getInstance();
//		cal2.setTime(time2);
//		cal2.set(Calendar.HOUR_OF_DAY, DateConvert.getTimeFromSql(time2, true, false, false));
//		cal2.set(Calendar.MINUTE, DateConvert.getTimeFromSql(time1, false, true, false));
//		cal2.set(Calendar.SECOND, 0);
//		cal2.set(Calendar.MILLISECOND, 0);
//		
//		if (cal1.equals(cal2))
//			return 0;
//		if (cal1.after(cal2))
//			return 1;
//		return -1;
//	}
	
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
	 * 
	 * @param date
	 * @return
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
	
	public static Time stringToSQLTime(String time)
	{
		return java.sql.Time.valueOf(time);
	}
	
}
