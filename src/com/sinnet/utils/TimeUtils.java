package com.sinnet.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class TimeUtils {

	public static long getCurrentTimeInMillSeconds() {
		return System.currentTimeMillis();
	}

	public static String getCurrentDateTimeStringInMinute() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		return format.format(date);
	}

	public static String getDateTimeStringInMinute(int time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date(time * 1000L);
		return format.format(date);
	}

	public static String getDateTimeStringInSeconds(int time) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time * 1000L);
		return format.format(date);
	}

	public static long getCurTimeInSeconds(String timeStr)
			throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(timeStr);
		return (date.getTime());
	}

	public static int getTimeInSeconds(String timeStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(timeStr);
		return (int) (date.getTime() / 1000L);
	}
	public static String getTimeFormtStr(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return  format.format(date);
	}

	public static String getDateString(int timeInSeconds) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(timeInSeconds * 1000L);
		return format.format(date);
	}

	public static String getTimeString(int timeInSeconds) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date = new Date(timeInSeconds * 1000L);
		return format.format(date);
	}

	public static String getCurrentDateString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return format.format(date);
	}
	public static String getCurrentDateTimeString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		return format.format(date);
	}

	/**
	 * 获取日期的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateOfDateString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	/**
	 * 获取日期的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getDateOfDateNian(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		return format.format(date);
	}

	/**
	 * 获取时间的字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getTimeOfDateString(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		return format.format(date);
	}

	public static String getHourAndMiniutesFromFullStr(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return TimeUtils.getTimeOfDateString(format.parse(timeStr));
		} catch (ParseException e) {
			Logger.getLogger(TimeUtils.class).error("parse timeStr error "+ timeStr,e);
		}
		return "";
	}
	
	/**
	 * 获取  10日星期三 格式内容
	 * @param timeStr
	 * @return
	 */
	public static String getDateAndXingqiByFullStr(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			
			Date date = format.parse(timeStr);
			int day =  date.getDate();
			int weekday = date.getDay();
			return day +"日"+covertDay(weekday);
		} catch (ParseException e) {
			Logger.getLogger(TimeUtils.class).error("parse timeStr error "+ timeStr,e);
		}
		return "";
	}
	
	/**
	 * 获取  10日星期三 格式内容
	 * @param timeStr
	 * @return
	 */
	public static String getFormatYYYYMMDDDateStr(String timeStr) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			
			Date date = format.parse(timeStr);
			return getDateOfDateString(date);
		} catch (ParseException e) {
			Logger.getLogger(TimeUtils.class).error("parse timeStr error "+ timeStr,e);
		}
		return "";
	}
	
	private static String covertDay(int weekday) {
		switch (weekday) {
		case 1:
			return "星期一";
		case 2:
			return "星期二";
		case 3:
			return "星期三";
		case 4:
			return "星期四";
		case 5:
			return "星期五";
		case 6:
			return "星期六";
		}
		return "星期日";
	}

	public static void main(String[] args) {
		System.out.println(getDateAndXingqiByFullStr("2015-12-9 12:00:00"));
	}

	/**
	 * 日期增加-按月增加
	 * 
	 * @param date
	 * @param days
	 * @return java.util.Date
	 */
	public static Date dateIncreaseByMonth(Date date, int mnt) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, mnt);

		return cal.getTime();
	}

	public static String formatTimeToMinuteAndSecond(int timeMills) {
		int time = timeMills / 1000;
		return String.format("%02d:%02d", time / 60, time % 60);
	}

	public static String formatMinutsToHourMinuteAndSecond(int mins) {
		return String.format("%02d:%02d:00", mins / 60, mins % 60);
	}

	public static String getApproveTimeGuid(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		return format.format(date);
	}

	public static String getApproveTimeGuid(int addNum, int hour, int minute) {
		return getApproveTimeGuid(getApproveTime(addNum, hour, minute));
	}

	public static Date getApproveTime(int addNum, int hour, int minute) {
		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_YEAR, addNum);

		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, minute);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		return date;
	}

}
