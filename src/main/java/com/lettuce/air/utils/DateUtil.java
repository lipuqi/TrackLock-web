package com.lettuce.air.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	//yyyymmddThhmmssZ 20151212T121212Z。
	public static Date parseDate(String eventTime) throws ParseException {
		String eTime[] = eventTime.split("T");
		String eTime1 = eTime[0];
		String eTime2 = eTime[1].substring(0, eTime[1].length() - 1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmss");
		Date strtodate = formatter.parse(eTime1 + eTime2);

		return strtodate;
	}
	
}
