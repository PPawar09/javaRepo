package com.pp.util;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * A number of conversion routines that may be needed by an application.
 * 
 * @version 1.0
 * @author Prakash Pawar
 */
public class Conversions {
	private static final String LOW_DATE_STRING_WITH_SLASH = "01/01/0001";
	private static final int YYYYMMDD_FORMAT_LENGTH = 8;
	private static final int YYYY_MM_DD_FORMAT_LENGTH = 10;
	
	private static final Logger logger = Logger.getLogger(Conversions.class.getName());
	
	/**
	 * Conversions constructor
	 */
	private Conversions() {}
	
	/**
	 * Perform the getDateForSqlDate method. convert sql date to calendar date.
	 * @return java.util.GregorianCalendar null is returned if null is passed.
	 * @param aDate java.sql.Date
	 */
	public static final Calendar getCalendarFromSQLDate(java.sql.Date aDate) { 
		if (aDate == null){
			return null;
		}
		java.util.GregorianCalendar retDate = new java.util.GregorianCalendar();
		retDate.setTime(aDate);
		return retDate;
	}
	
	/**
	 * Simple method to convert a util date to a sql date.
	 * @param date
	 * @return
	 */
	public static final java.sql.Date convertToSQLDate(java.util.Date date) {
		if (date == null){
			return null;
		}
		else{
			return new java.sql.Date(date.getTime());
		}
	}
	
	/**
     * Static factory method to return a Date object, given the year, month and date.
     * This method will include the current time with the date.
     * @param year Year to be converted
     * @param month Zero based month 
     * @param date Day of the month
     * @return
     */
	public static Date getDate(int year, int month, int date)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, date);
		Date d = new Date(cal.getTimeInMillis());
		return d;
	}

	/**
	 * Checks if the passed column value
	 * @param colValue
	 * @return
	 */
	public static final String trimColumn(String colValue) {
		return StringUtils.trim(colValue);
	}
	
	/**
	 * Convert String(MM/DD/YYYY) to Date  
	 * @param input String Date must be in MM/DD/YYYY format
	 * @return java.util.Date
	 */
	public static final Date convertToDate(String input) {
		return convertToDate(input, Constants.DATE_FORMAT_MMDDYYYY_WITHSLASH);
	}
	
	/**
	 * Convert from a String to a Date. Take also Format of the Date as a parameter
	 * the date formats supported can placed in constants class.
	 * @param input String
	 * @param dateFormat
	 * @return java.util.Date
	 */
	public static final Date convertToDate(String input, String dateFormat) {
		try {
			if (input != null && dateFormat != null){
				return getSimpleDateFormat(dateFormat).parse(input);
			} else{
				return null;
			}
		} catch (Exception e) {
				logger.log(Level.FINER,"Could not convert "	+ input + " to a Date:", e);
			return null;
		}
	}
	
	/**
	 * Convert from a String to an int.
	 * This method will return a 0 if the input is null or cannot be converted.
	 * @param input String
	 * @return int Default value is 0
	 */
	public static final int convertToInt(String input) {
		return NumberUtils.stringToInt(input, 0);
	}

	/**
	 * Convert from a String to an int.  This method will throw and exception 
	 * if the String is null or cannot be converted.
	 * @param input String
	 * @exception NumberFormatException
	 * @return int
	 */
	public static final int convertToIntStrict(String input)
			throws NumberFormatException {
		int convertedInt = 0;
		try {
			if (input == null){
				throw new NumberFormatException("Value null.");
			}
			convertedInt = Integer.parseInt(input);
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
		}
		return convertedInt;
	}

	/**
	 * Convert a String to a float
	 * @param input String
	 * @return float
	 */
	public static final float convertToFloat(String input) {
		try {
			return new Float(input).floatValue();
		} catch (NumberFormatException nfe) {
			return 0.0f;
		}
	}
	
	
	/**
	 * Convert a String to a double
	 * @param input String
	 * @return double
	 */
	public static final double convertToDouble(String input) {
		try {
			return new Double(input).doubleValue();
		} catch (NumberFormatException nfe) {
			return 0.0f;
		}
	}
	
	/**
	 * Overloaded method that will convert a java.util.Date into a String.
	 * @param input java.util.Date
	 * @return String value of the date in MM/DD/YYYY format.
	 */
	public static final String convertToString(java.util.Date input) {
		return convertToString(	input,Constants.DATE_FORMAT_MMDDYYYY_WITHSLASH);
	}
	
	/**
	 * Take a java.util.Date and return a String in the format sent to the
	 * method.  See Constants or SimpleDateFormat for patterns.
	 * @param input A date to convert
	 * @param dateFormat A date format that the method will use.
	 * @return A java.util.Date
	 * @see SimpleDateFormat
	 * @see Date
	 */
	public static final String convertToString(java.util.Date input, String dateFormat) {
		String retVal = "";
		if (input == null || dateFormat == null){
			return LOW_DATE_STRING_WITH_SLASH;
		}

		try {
			retVal = getSimpleDateFormat(dateFormat).format(input);
		} catch (IllegalArgumentException npe) {
			logger.warning("Could not convert " + input +" a to String:" + npe.getMessage());
			retVal = "";
		} catch (Exception e) {
			logger.warning("Could not convert " + input +" a to String:" + e.getMessage());
			retVal = "";
		}
		return retVal;
	}
		
	/**
	 * Method is used by applications where null date fields are not allowed
	 * in the database.  If the date is null, then a low date of  01/01/0001 is returned.
	 * @param date
	 * @return
	 */
	public static final Date convertDateFromNull(Date date) {
		if (date == null) {
			Calendar cal = new GregorianCalendar(1, 0, 1);
			return cal.getTime();
		} else {
			return date;
		}
	}
	
	/**
	 * Overloaded method that will convert an int into a String
	 * @param input int
	 * @return String
	 */
	public static final String convertToString(int input) {
		return Integer.toString(input);
	}
	
	/**
	 * Overloaded method that will convert a float into a String
	 * @param input float
	 * @return String
	 */
	private static final DecimalFormat twoDigitFloatFormat = new DecimalFormat("0.00"); 
	public static final String convertToString(float input) {
		return twoDigitFloatFormat.format(input);
	}
	
	/**
	 * Overloaded method that will convert a TimeStamp object to a String
	 * @param input java.sql.Timestamp
	 * @return String
	 */
	public static final String convertToString(Timestamp input) {
		if (input == null){
			return "";
		}
		else{
			return input.toString();
		}
	}
	
	/**
	 * Method that will convert a TimeStamp object to a Date String
	 * in the form of MM/DD/YYYY
	 * @param _input java.sql.Timestamp
	 * @return String
	 */
	public static final String convertToDateString(Timestamp input) {
		if (input == null){
			return "";
		}
		else {
			return getSimpleDateFormat("MM/dd/yyyy").format(input);
		}
	}

	/**
	 * Overloaded method that will convert a boolean object to a String
	 * @param b boolean
	 * @return String
	 */
	public static final String convertToString(boolean b) {
		return b ? "Y" : "N";
	}
	
	/**
	 * Method that will convert a String to a boolean 
	 * @param input java.util.String
	 * @return boolean
	 */
	public static final boolean convertToBoolean(String input) {
		return "Y".equalsIgnoreCase(input) ? true : false;
	}
	
	/**
	 * Format a float value as a dollar value.  No comma is included.
	 * If 0.0f is passed then $0.00 will be returned. 1000.00 will be 
	 * returned as $1000.00
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param input
	 * @return Dollar value of the float.  
	 */
	public static final String convertFloatToDollar(float input){
		return convertFloatToFormat(input, dfDollarDecimal);
	}
	
	/**
	 * Format a float value as a dollar value that includes a comma separator.  
	 * If 0.0f is passed then $0.00 will be returned. 1000.00 will be 
	 * returned as $1,000.00 
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param _input
	 * @return Dollar value of the float with comma separator.  
	 */
	public static final String convertFloatToDollarWithComma(float input){
		return convertFloatToFormat(input, dfDollarCommaDecimal);
	}

	/**
	 * Format a float value as a whole dollar value that includes a comma separator.  
	 * If 0.0f is passed then $0 will be returned. 1000.00 will be 
	 * returned as $1,000 
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param input
	 * @return Whole dollar value of the float with comma separator.  
	 */
	public static final String convertFloatToWholeDollarWithComma(float input){
		return convertFloatToFormat(input, dfDollarComma);
	}

	private static String convertFloatToFormat(float _input, DecimalFormat floatFormat){
		return floatFormat.format(_input);
	}
	
	private static final DecimalFormat dfDollarCommaDecimal = new DecimalFormat("$###,###,##0.00");
	private static final DecimalFormat dfDollarComma  = new DecimalFormat("$###,###,##0");
	private static final DecimalFormat dfDollarDecimal = new DecimalFormat("$0.00");
	private static final DecimalFormat dfDollar = new DecimalFormat("$0");
	
	/**
	 * Format a double value as a dollar value.  No comma is included.
	 * If 0.0f is passed then $0.00 will be returned. 1000.00 will be 
	 * returned as $1000.00
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param input
	 * @return Dollar value of the double.  
	 */
	public static final String convertDoubleToDollar(double input){
		return convertDoubleToFormat(input, dfDollarDecimal);
	}
	
	/**
	 * Format a float value as a dollar value that includes a comma separator.  
	 * If 0.0f is passed then $0.00 will be returned. 1000.00 will be 
	 * returned as $1,000.00 
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param input
	 * @return Dollar value of the double with comma separator.  
	 */
	public static final String convertDoubleToDollarWithComma(double input){
		return convertDoubleToFormat(input, dfDollarCommaDecimal);
	}

	/**
	 * Format a double value as a whole dollar value that includes a comma separator.  
	 * If 0.0f is passed then $0 will be returned. 1000.00 will be 
	 * returned as $1,000 
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param input
	 * @return Whole dollar value of the double with comma separator.  
	 */
	public static final String convertDoubleToWholeDollarWithComma(double input){
		return convertDoubleToFormat(input, dfDollarComma);
	}

	/**
	 * Format a double value as a whole dollar value.  
	 * If 0.0f is passed then $0 will be returned. 1000.00 will be 
	 * returned as $1000 
	 * The value will also be rounded to two digits after the decimal.
	 *  
	 * @param _input
	 * @return Whole dollar value of the double.  
	 */
	public static final String convertDoubleToWholeDollar(double input){
		return convertDoubleToFormat(input, dfDollar);
	}
	
	private static String convertDoubleToFormat(double input, DecimalFormat numFormat){
		return numFormat.format(input);
	}
	
	/**
	 * SimpleDateFormat is multi-thread safe ...
	 * @param format
	 * @return SimpleDateFormat
	 */
	private static SimpleDateFormat getSimpleDateFormat(String format){
		return new SimpleDateFormat(format);
	}
	
}
