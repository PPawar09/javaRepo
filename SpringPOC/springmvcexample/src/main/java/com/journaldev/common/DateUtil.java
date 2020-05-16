package com.journaldev.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * Sample format in put "yyyy-MM-dd"
	 * @param sdf
	 * @return
	 */
	public static String getCurrentSdfDate(String sdf){
		String currDate = null;
		
		DateFormat f = new SimpleDateFormat(sdf);
		Date dt = new Date();
		currDate = f.format(dt);
		
		return currDate;
	}

}
