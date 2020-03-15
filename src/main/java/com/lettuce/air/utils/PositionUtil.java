package com.lettuce.air.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class PositionUtil {

	public static String[] parseLonlan(String latitude, String longitude) {
		// 数据格式化
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.applyPattern("#.000000");
		int index_lon = longitude.indexOf(".") - 2;
		int index_lat = latitude.indexOf(".") - 2;
		double lon = Double.parseDouble(longitude.substring(0, index_lon)) + Double.parseDouble(longitude.substring(index_lon)) / 60;
		double lat = Double.parseDouble(latitude.substring(0, index_lat)) + Double.parseDouble(latitude.substring(index_lat)) / 60;
		
		double[] gaodeGps = GpsUtil.toGCJ02Point(lat, lon, 7);// 进行纠偏
		String[] res = {decimalFormat.format(gaodeGps[0]), decimalFormat.format(gaodeGps[1])};
		return res;
	}
	
	public static Date parseUTCtime(String time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		Date strtodate = formatter.parse(time);
        Calendar c = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        c.setTime(strtodate);
        c.set(Calendar.YEAR, b.get(Calendar.YEAR));
        c.set(Calendar.MONTH, b.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, b.get(Calendar.DAY_OF_MONTH));
		
		return c.getTime();
	}
	
}
