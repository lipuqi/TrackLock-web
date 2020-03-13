package com.lettuce.air.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PositionUtil {

	public static String parseLonlan(String lonlan) {
		// 数据格式化
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("#.00000");
		double a = Double.valueOf(lonlan);
		int a1 = (int) ((a) / 100);
		double a2 = (a) % 100.0 / 60;
		double a3 = a1 + a2;

		return decimalFormat.format(a3);
	}
	
	public static Date parseUTCtime(String time) throws ParseException {
		// 数据格式化
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss.SSS");
		Date strtodate = formatter.parse(time);
        Calendar c = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        c.setTime(strtodate);
        c.set(Calendar.YEAR, b.get(Calendar.YEAR));
        c.set(Calendar.HOUR, b.get(Calendar.HOUR) + 9);
		
		return c.getTime();
	}

}
