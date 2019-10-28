package br.ufma.lsdi.interscity.mamager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {
	
	public static Date strToDate(String formatDate, String formatHour,String date, String hour) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate+" "+formatHour);
		Date d;
		try {
			d = dateFormat.parse(date+" "+hour);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date addHoursToJavaUtilDate(Date date, int millisec) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.add(Calendar.MILLISECOND, millisec);
	    return calendar.getTime();
	}
	
	public static String convertDate(String formatDate, String formatHour, Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formatDate);
		String strDate = dateFormat.format(data) + "T";
		dateFormat = new SimpleDateFormat(formatHour);
		strDate += dateFormat.format(data) + "Z";
		return strDate;
	}

	public static String convertDate(Date data) {
		return convertDate("yyyy-MM-dd", "hh:mm:ss.SSS", data);
	}

	public static Date unixDate(String data) {
		return new Date((long) Double.parseDouble(data));
	}
}
