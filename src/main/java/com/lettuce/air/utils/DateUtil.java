package com.lettuce.air.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	public static Date parseUTCtime(String time) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("HHmmss.SSS");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date strtodate = formatter.parse(time);
        Calendar c = Calendar.getInstance();
        Calendar b = Calendar.getInstance();
        c.setTime(strtodate);
        c.set(Calendar.YEAR, b.get(Calendar.YEAR));
        c.set(Calendar.MONTH, b.get(Calendar.MONTH));
        c.set(Calendar.DAY_OF_MONTH, b.get(Calendar.DAY_OF_MONTH));
        c.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
		return c.getTime();
	}
	
	//yyyymmddThhmmssZ 20151212T121212Z。
	public static Date parseDate(String eventTime) throws ParseException {
		String eTime[] = eventTime.split("T");
		String eTime1 = eTime[0];
		String eTime2 = eTime[1].substring(0, eTime[1].length() - 1);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyymmddhhmmss");
		formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date strtodate = formatter.parse(eTime1 + eTime2);
		return strtodate;
	}
	
	 //获取系统当前时间，字符串类型
    public static String getStrDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date nowDate = new Date();
        String dateStr = sdf.format(nowDate);
        return dateStr;
    }
 
    //获取系统当前时间Date类型，需要将字符串类型转成时间
    public static Date getDaDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //设置为东八区
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        Date nowDate = new Date();
        String dateStr = sdf.format(nowDate);
 
        //将字符串转成时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDate = null;
        try {
            newDate = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
    
}
