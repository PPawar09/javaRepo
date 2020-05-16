package com.pp.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

/**
 * A set of Date utilities to augment the utilities provided in Jakarta Commons DateUtils
 * 
 * @author prakash pawar
 */
public class DateUtil {

	private static final Logger logger = Logger.getLogger(DateUtil.class.getName());
	
	private DateUtil(){
		
	}

	/**
	 * This method used to add or subtract days or month on date.
	 * @param date
	 * @param calendarField eg Calendar.DAY_OF_MONTH
	 * @param numberOfUnits as per calendarField like number day or month.
	 * @return
	 */
	private static Date moveDate(Date date, int calendarField, int numberOfUnits) {
		if (date == null)
			return null;
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(calendarField, numberOfUnits);
		return calender.getTime();
	}

	/**
	 * This function adds the specifed months to the given date (in the form of MM/DD/YYYY
	 * and returns the final date in the same format.
	 * @param dateString A date in MM/DD/YYYY format
	 * @param numberOfMonths
	 * @return
	 */
	public static String addMonthsToDate(String dateString, int numberOfMonths) {
		Date d = Conversions.convertToDate(dateString);
		if (d != null){
			return Conversions.convertToString(moveDate(d, Calendar.MONTH, numberOfMonths));
		}
		else 
			return "";
	}
	
	/**
	 * This function adds the specifed months to the given date and returns the final date
	 * @param date
	 * @param numberOfMonths
	 * @return
	 */
	public static Date addMonthsToDate(Date date, int numberOfMonths) {
		return moveDate(date, Calendar.MONTH, numberOfMonths);
	}

	/**
	 * This function subtracts the specifed months from the given date in MM/DD/YYYY format
	 * and returns the final date in MM/DD/YYYY format.
	 * @param date
	 * @param numberOfMonths
	 * @return
	 */
	public static String subtractMonthsFromDate(String dateString, int numberOfMonths) {
		Date d = Conversions.convertToDate(dateString);
	if (d != null){
		return Conversions.convertToString(moveDate(d, Calendar.MONTH, (numberOfMonths * -1)));
	}
	else 
		return "";
	}
	
	/**
	 * This function subtracts the specifed months from the given date and returns 
	 * the final date
	 * @param date
	 * @param numberOfMonths
	 * @return
	 */
	public static Date subtractMonthsFromDate(Date date, int numberOfMonths) {
		return moveDate(date, Calendar.MONTH, (numberOfMonths*-1));
	}

	/**
	 * This function adds the specifed days to the given date in MM/DD/YYYY format
	 * and returns the final date in MM/DD/YYYY format.
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static String addDaysToDate(String dateString, int numberOfDays) {
		Date d = Conversions.convertToDate(dateString);
		if (d != null){
			return Conversions.convertToString(moveDate(d, Calendar.DATE, numberOfDays));
		}
		else 
			return "";
	}
	
	/**
	 * This function subtracts the specifed days from the given date in MM/DD/YYYY format
	 * and returns the final date in MM/DD/YYYY format.
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static String subtractDaysFromDate(String dateString, int numberOfDays) {
		Date d = Conversions.convertToDate(dateString);
		if (d != null){
			return Conversions.convertToString(moveDate(d, Calendar.DATE, (numberOfDays*-1)));
		}
		else 
			return "";

	}

	/**
	 * This function adds the specifed days to the given date and returns 
	 * the final date
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static Date addDaysToDate(Date date, int numberOfDays) {
		return moveDate(date, Calendar.DATE, numberOfDays);
	}
	
	/**
	 * This function subtracts the specifed days from the given date and 
	 * returns the final date
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static Date subtractDaysFromDate(Date date, int numberOfDays) {
		return moveDate(date, Calendar.DATE, (numberOfDays*-1));	
	}

	/**
	 * This function adds the specifed years to the given date and returns 
	 * the final date
	 * @param date
	 * @param numberOfYears
	 * @return
	 */
	public static Date addYearsToDate(Date date, int numberOfYears) {
		return moveDate(date, Calendar.YEAR, numberOfYears);
	}
	
	/**
	 * This function subtracts the specifed years from the given date and 
	 * returns the final date
	 * @param date
	 * @param numberOfYears
	 * @return
	 */
	public static Date subtractYearsFromDate(Date date, int numberOfYears) {
		return moveDate(date, Calendar.YEAR, (numberOfYears*-1));
	}	
	/**
	 * This function adds the specifed years to the given datein MM/DD/YYYY format
	 * and returns the final date in MM/DD/YYYY format.
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static String addYearsToDate(String dateString, int numberOfYears) {
		Date d = Conversions.convertToDate(dateString);
		if (d != null){
			return Conversions.convertToString(moveDate(d, Calendar.YEAR, numberOfYears));
		}
		else 
			return "";	
	}
	
	/**
	 * This function subtracts the specifed years from the given datein MM/DD/YYYY format
	 * and returns the final date in MM/DD/YYYY format.
	 * @param date
	 * @param numberOfDays
	 * @return
	 */
	public static String subtractYearsFromDate(String dateString, int numberOfYears) {
		Date d = Conversions.convertToDate(dateString);
		if (d != null){
			return Conversions.convertToString(moveDate(d, Calendar.YEAR, (numberOfYears *-1)));
		}
		else 
			return "";
	}	


	/**
	 * Method checkes whether the given date is greater than the days passed
	 * as a parameter
	 * @param theDate String representing a date
	 * @param dateFormat Format of the date string
	 * @param days number of days to compare with
	 * @return
	 */
	public static boolean isDateGreaterEqualPlusDays(
		String theDate,
		String dateFormat,
		int days) {
		Date parsedDate = Conversions.convertToDate(theDate, dateFormat);
		return DateUtil.isDateGreaterEqualPlusDays(parsedDate, days);
	}	
	
	/**
	 * Method checkes whether the given date is greater than the days passed
	 * as a parameter
	 * @param theDate String representing a date
	 * @param dateFormat Format of the date string
	 * @param days number of days to compare with
	 * @return
	 */
	public static boolean isDateGreaterEqualPlusDays(
		Date theDate,
		int days) {
		GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return theDate.after(cal.getTime());
	}		
	/**
	 * Method checkes whether the given date still less than the days passed
	 * as a parameter
	 * @param theDate String representing a date
	 * @param dateFormat Format of the date string
	 * @param days number of days to compare with
	 * @return
	 */
	public static boolean isDateLessEqualMinusDays(
			Date theDate, int days) {
		try {
			//Set the time for today
		  	Date dt = new Date(System.currentTimeMillis());
		  	//Now set the time for the calendar
			Calendar cal = Calendar.getInstance();
			cal.setTime(theDate);
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) + days);
		    return dt.after(cal.getTime());
		} catch (Exception e) {
			logger.warning("Error in isDateLessEqualMinusDays: " + e.toString());			
		    return false;
		}
	}
	
	/**
	 * Method return number of days between two dates
	 * as a parameter
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	public static int getDaysDiff(Date startDt, Date endDt){
		int diff = 0;
	    long difference = (startDt.getTime()-endDt.getTime())/86400000; 
	    diff = (int)Math.abs(difference);
	    
	    return diff; 
	}
	
	
	/**
	 * Method return number of days between two dates
	 * as a parameter
	 * @param startDt
	 * @param endDt
	 * @return
	 */
	public static String getCurrentSdfDate(String sdf){
		String currDate = null;
		
		DateFormat f = new SimpleDateFormat(sdf);
		Date dt = new Date();
		currDate = f.format(dt);
		
		return currDate;
	}
	
	
	
	public static void main(String args[])throws Exception{
		String s1 = "18-09-2014"; 
	    String s2 = "18-09-2015";
	    DateFormat f = new SimpleDateFormat("dd-MM-yyyy");
	    Date startDt = f.parse(s1); 
	    Date endDt = f.parse(s2);
		int dayDiff = DateUtil.getDaysDiff(startDt, endDt);
		System.out.println("********" + dayDiff);
	}
}