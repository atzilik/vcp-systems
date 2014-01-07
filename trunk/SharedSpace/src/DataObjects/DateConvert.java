package DataObjects;

import java.util.Calendar;

public class DateConvert {


	public static boolean equalsDateAndSql(java.util.Date date1, java.sql.Date date2){
		java.util.Date newdate2 = fixDate(date2);
		return date1.equals(newdate2);
	}
	
	public static boolean afterDateAndSql(java.util.Date date1, java.sql.Date date2){
		java.util.Date newdate2 = fixDate(date2);
		return date1.after(newdate2);
	}
	
	public static boolean beforeDateAndSql(java.util.Date date1, java.sql.Date date2){
		java.util.Date newdate2 = fixDate(date2);
		return date1.before(newdate2);
	}
	/**
	 * 0  - same time
	 * 1  - first later time
	 * -1 - second later time 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static int compareTime(java.sql.Time time1, java.sql.Time time2)
	{
		String newtime1 = time1.toString();
		String newtime2 = time2.toString();
		String hour1 = newtime1.substring(0, 2);
		String hour2 = newtime2.substring(0, 2);
		String minutes1 = newtime1.substring(3, 5);
		String minutes2 = newtime2.substring(3, 5);
		int finalHour1 = Integer.parseInt(hour1);
		int finalHour2 = Integer.parseInt(hour2);
		int finalMin1 = Integer.parseInt(minutes1);
		int finalMin2 = Integer.parseInt(minutes2);
		
		if (finalHour1 > finalHour2)
			return 1;
		if (finalHour1 < finalHour2)
			return -1;
		if (finalMin1 > finalMin2)
			return 1;
		if (finalMin1 < finalMin2)
			return -1;
		return 0;
	}
	
	public static java.util.Date fixDate(java.sql.Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	public static java.sql.Time fixTime(java.sql.Time time){
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		cal.set(Calendar.MILLISECOND, 0);
		return new java.sql.Time(cal.getTime().getTime());
	}
}
