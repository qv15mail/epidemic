package com.supwisdom.datashow.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	public static final String[] alias = new String[] { "d", "h", "m", "s", "ms" };

	public static final long[] duration = new long[] { 86400000, 3600000, 60000, 1000, 1 };

	private static final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * 自动体检时间 HHmmss
	 */
	// 凌晨2点 02:00:00
	private static final String checkTime = "020000";

	/*
	 * Description: 返回一个当前时间 @return String 格式：yyyyMMddHHmmss @exception Modify
	 * History:
	 */

	public static String getNow() {
		if (logger.isDebugEnabled()) {
			logger.debug("getNow() - start"); //$NON-NLS-1$
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String returnString = sdf.format(new Date());
		if (logger.isDebugEnabled()) {
			logger.debug("getNow() - end"); //$NON-NLS-1$
		}
		return returnString;
	}

	/*
	 * Description: 根据类型返回一个当前时间 @param partten String @return String 格式：partten
	 *
	 * @exception Modify History:
	 */

	public static String getNow(String partten) {
		if (logger.isDebugEnabled()) {
			logger.debug("getNow() - start"); //$NON-NLS-1$
		}

		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		String returnString = sdf.format(new Date());
		if (logger.isDebugEnabled()) {
			logger.debug("getNow() - end"); //$NON-NLS-1$
		}
		return returnString;
	}

	/*
	 * Description: 得到一个特殊的时间 @param startTime String 格式：yyyyMMddHHmmss @param
	 * interval int 秒 @return String 格式：partten @exception Modify History:
	 */

	public static String getNewTime(String startTime, int interval) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = sdf.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.SECOND, interval);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	/*
	 * Description: 得到一个特殊的时间 @param startTime String 格式：yyMMddHHmmss @param
	 * interval int 秒 @return String 格式：partten @exception Modify History:
	 */

	public static String getSpecialNewTime(String startTime, int interval) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
			Date d = sdf.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.SECOND, interval);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	/*
	 * Description: 根据时间形式，得到加上间隙时间后的第二个时间
	 * @param startTime String 格式：partten
	 * @param interval int 秒
	 * @return String 格式：partten @exception Modify History:
	 */

	public static String getNewTimes(String startTime, int interval, String partten) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(partten);
			Date d = sdf.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.SECOND, interval);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	/*
	 * Description: 得到两个时间字符串之间的间隙时间 @param firstTime String 格式：yyyyMMddHHmmss
	 *
	 * @param secondTime String 格式: yyyyMMddHHmmss @return long @exception
	 * parseException Modify History:
	 */
	public static long getIntervalTime(String firstTime, String secondTime) {
		long time = 000000000;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date f = sdf.parse(firstTime);
			Date s = sdf.parse(secondTime);
			time = f.getTime() - s.getTime();
			return time;
		} catch (ParseException e) {
			// TODO: handle exception
			return time;
		}
	}

	/*
	 * Description: 比较两个时间字符串的前后关系 @param firstTime String 格式：yyyyMMddHHmmss
	 *
	 * @param secondTime String 格式: yyyyMMddHHmmss @return int | firstTime=second
	 * int=0 | firstTime>secondTime int>0 | firstTime<secondTime int<0 @exception
	 * Modify History:
	 */

	public static int checkTimeSequence(String firstTime, String secondTime) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date f = sdf.parse(firstTime);
			Date s = sdf.parse(secondTime);
			return f.compareTo(s);
		} catch (ParseException e) {
			// TODO: handle exception
			return 0;
		}
	}

	/*
	 * Description: 比较两个时间字符串的前后关系 @param firstTime String 格式：yyyyMMddHHmmss
	 *
	 * @param secondTime String 格式: yyyyMMddHHmmss @return int | firstTime=second
	 * int=0 | firstTime>secondTime int>0 | firstTime<secondTime int<0 @exception
	 * Modify History:
	 */

	public static int checkSpecialTimeSequence(String firstTime, String secondTime, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date f = sdf.parse(firstTime);
			Date s = sdf.parse(secondTime);
			return f.compareTo(s);
		} catch (ParseException e) {
			// TODO: handle exception
			return 0;
		}
	}

	/**
	 * Description: 比较两个时间字符串的时间差
	 *
	 * @param firstTime
	 *          String 格式：yyyyMMddHHmmss
	 * @param secondTime
	 *          String 格式: yyyyMMddHHmmss
	 *          int 格式 @return int | firstTime+seconds=secondTime int=0 |
	 *          firstTime+seconds>secondTime int>0 | firstTime+seconds<secondTime
	 *          int<0
	 */

	public static int checkIntervalTime(String firstTime, String secondTime, int seconds) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date f = sdf.parse(firstTime);
			Date s = sdf.parse(secondTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(f.getTime());
			calendar.add(Calendar.SECOND, seconds);
			Date temp = calendar.getTime();
			return temp.compareTo(s);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}

	public static int getDayofWeek() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

	public static String reFormatTime(String time) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = sdf.parse(time);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			return sdf.format(calendar.getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return time;
		}
	}

	/**
	 * 将yyyyMMddHHmmss格式的字符串日期与当前时间比较,结果精确到秒
	 *
	 * @param startTime
	 *          .
	 * @return .
	 * @throws ParseException .
	 */
	public static String convertTime(String startTime) throws ParseException {
		return convertTime(startTime, "yyyyMMddHHmmss", 4);
	}

	/**
	 * 将指定格式的字符串日期与当前时间比较
	 *
	 * @param startTime
	 *          .
	 * @param pattern
	 *          .
	 * @param precision
	 *          .
	 * @return .
	 * @throws ParseException .
	 */
	public static String convertTime(String startTime, String pattern, int precision) throws ParseException {
		if (StringUtil.isEmpty(startTime)) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date startDate = dateFormat.parse(startTime);
		long interval = System.currentTimeMillis() - startDate.getTime();
		return convertTime(interval, duration, alias, precision);
	}

	/**
	 * 将指定的毫秒数转换成对应的时间长度字符串
	 *
	 * @param interval
	 *          .
	 * @param duration
	 *          .
	 * @param alias
	 *          .
	 * @param precision
	 *          精度，例如精确到天，该值为1，精确到毫秒，改值为5
	 * @return
	 */
	public static String convertTime(long interval, long[] duration, String[] alias, int precision) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < precision; i++) {
			long div = interval / duration[i];
			interval = interval % duration[i];
			result.append(div).append(alias[i]).append(" ");
		}
		if (result.length() > 0) {
			result = result.deleteCharAt(result.length() - 1);
		}
		return result.toString();
	}

	/**
	 * Description: string 格式的日期转换成java.util.Date格式的日期
	 *
	 * @param @param datetime
	 * @param @param pattern
	 * @param @return
	 * @param @throws ParseException
	 * @return Date Modify History: 2010-8-5 Linqing.He create
	 */
	public static Date getDate(String datetime, String pattern) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		Date startDate = dateFormat.parse(datetime);
		return startDate;
	}

	/**
	 * 获得两个字符串日期之间的时间差(单位毫秒)
	 *
	 * @param startTime
	 *          .
	 * @param endTime
	 *          .
	 * @return .
	 */
	public static long getDuration(String startTime, String endTime) {
		long duration = 0;
		try {
			duration = dateFormat.parse(endTime).getTime() - dateFormat.parse(startTime).getTime();
		} catch (ParseException e) {
			logger.error("Hi guys,there is an error when you try to parse the date string");
		}
		return duration;
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static Date getDate(String date) throws ParseException {
		return dateFormat.parse(date);
	}

	/**
	 * 获取某一个时间段内的有效时间长度
	 *
	 * @param startTime
	 *          .
	 * @param endTime
	 *          .
	 * @param minTime
	 *          .
	 * @param maxTime
	 *          .
	 * @return .
	 */
	public static long getDuration(String startTime, String endTime, Date minTime, Date maxTime) {
		long duration = 0;
		try {
			long low = DateUtil.getDate(startTime).getTime();
			long high = DateUtil.getDate(endTime).getTime();
			if (low < minTime.getTime()) {
				low = minTime.getTime();
			}
			if (high > maxTime.getTime()) {
				high = maxTime.getTime();
			}
			duration = high - low;
		} catch (ParseException e) {
			logger.error("Hi guys,there is an error when you try to parse the date string");
		}
		return duration;
	}

	/**
	 * Description:
	 *
	 * @param @param startTime
	 * @param @param interval
	 * @param @return
	 * @return String Modify History: 2010-9-10 Linqing.He create
	 */
	public static String getSpecialCardver(String startTime, int interval) {
		String time = startTime.substring(0, 6);
		String cardver = startTime.substring(6);

		int cardint = Integer.parseInt(cardver);
		cardint = cardint - interval;

		String cardnewver = "000000";

		if (cardint > 0) {
			cardnewver = "000000" + String.valueOf(cardint);
		}

		cardnewver = cardnewver.substring(cardnewver.length() - 6);

		return time + cardnewver;

	}

	/**
	 * Description: 取得一天的开始，例：20140421000000
	 *
	 * @return String
	 */
	public static String getDayStartTime(String startTime, int day, int hour, int minute, int second) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = sdf.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, day);
			calendar.set(Calendar.HOUR_OF_DAY, hour);
			calendar.set(Calendar.MINUTE, minute);
			calendar.set(Calendar.SECOND, second);
			return sdf.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}

	}

	/**
	 * Description: 长格式化为短格式，例:20140422000000-20140422
	 *
	 * @param startTime
	 * @param longPartten
	 * @param shortPartten
	 * @param day
	 * @return String Modify History: 2010-9-10 Linqing.He create
	 */
	public static String longToShortDate(String startTime, String longPartten, String shortPartten, int day) {
		try {
			SimpleDateFormat longFormat = new SimpleDateFormat(longPartten);
			SimpleDateFormat shortFormat = new SimpleDateFormat(shortPartten);
			Date d = longFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return shortFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	/**
	 * Description: 日期DAY_OF_MONTH+=day
	 *
	 * @param startTime
	 * @param day
	 * @return String Modify History: 2010-9-10 Linqing.He create
	 */
	public static String strDateDayAdd(String startTime, int day) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
			Date d = dateFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}
	
	public static String strDateDayAdd2(String startTime, int day) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date d = dateFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}


	/**
	 * Description: 日期(长日期yyyyMMddhhmmss)DAY_OF_MONTH+=day
	 *
	 * @param startTime
	 * @param day
	 * @return String Modify History: 2010-9-10 Linqing.He create
	 */
	public static String strLongDateDayAdd(String startTime, int day) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = dateFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, day);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	/**
	 * Description: 日期(长日期yyyyMMddhhmmss)MONTH+=month
	 *
	 * @param startTime
	 * @param month
	 * @return String Modify History: 2010-9-10 Linqing.He create
	 */
	public static String strLongDateMonthAdd(String startTime, int month) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date d = dateFormat.parse(startTime);
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(d.getTime());
			calendar.add(Calendar.MONTH, month);
			return dateFormat.format(calendar.getTime());
		} catch (ParseException e) {
			return startTime;
		}
	}

	public static String getDateByCode(int selectCode) {
		/**
		 * 1.最近一周的情况 2.最近一月的情况 3.最近三月的情况
		 * */
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyyMMdd");
		String startDate = "";
		switch (selectCode) {
		case 1:
			calendar.add(Calendar.DATE, -7);
			startDate = sFormat.format(calendar.getTime());
			break;
		case 2:
			calendar.add(Calendar.MONTH, -1);
			startDate = sFormat.format(calendar.getTime());
			break;
		case 3:
			calendar.add(Calendar.MONTH, -3);
			startDate = sFormat.format(calendar.getTime());
			break;
		default:
			break;
		}
		return startDate;
	}

	public static String getScoreStartDate() {
		/**
		 * 1.最近一周的情况 2.最近一月的情况 3.最近三月的情况
		 * */
		String startdate = "";
		String mon = DateUtil.getNow("MM");
		if (Integer.valueOf(mon) >= 3) {
			startdate = DateUtil.getNow("yyyy") + "0101";
		} else {
			startdate = Integer.valueOf(DateUtil.getNow("yyyy")) - 1 + "0101";
		}
		return startdate;
	}

	public static String getScoreStartDate(String statTime) {
		/**
		 * 1.最近一周的情况 2.最近一月的情况 3.最近三月的情况
		 * */
		if (statTime.length() < 6) {
			return "";
		}
		String startdate = "";
		String mon = statTime.substring(4, 6);
		if (Integer.valueOf(mon) >= 3) {
			startdate = statTime.substring(0, 4) + "0101";
		} else {
			startdate = Integer.valueOf(statTime.substring(0, 4)) - 1 + "0101";
		}
		return startdate;
	}

	/**
	 * 两段日期的天数差
	 *
	 * @param firstDay
	 * @param secondDay
	 * @return 天数差
	 * */
	public static long getIntervalDay(String firstDay, String secondDay) {
		long time = 000000000;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date f = sdf.parse(firstDay);
			Date s = sdf.parse(secondDay);
			time = s.getTime() - f.getTime();
			return time / (24 * 60 * 60 * 1000);
		} catch (ParseException e) {
			return 0;
		}
	}

	public static String getDayofWeek(int offset_week, int day_of_week, String format) {
		Calendar cal = Calendar.getInstance();
		// n为推迟的周数，1本周，-1向前推迟一周，2下周，依次类推
		String retday;
		cal.add(Calendar.DATE, offset_week * 7);
		cal.set(Calendar.DAY_OF_WEEK, day_of_week);
		retday = new SimpleDateFormat(format).format(cal.getTime());
		return retday;

	}
	
	public static String getNowFmt(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnString = sdf.format(new Date());
        return returnString;
	}

	/**
	 * 取本学年的开始日期
	 * @return
	 */
	public static String getTermDate(){
		String nowdate = getNow("yyyyMMdd");
		String year = nowdate.substring(0,4);
		if (DateUtil.getIntervalDay(year+"0830", nowdate) > 0){
			//return year+"0801";
			return year+"0601";
		}else{
			return String.valueOf(Integer.parseInt(year)-1)+"0830";
		}
	}

	/**
	 * 取本学年开始日期至今隔了多少个月
	 * @return
	 */
	public static Integer getMonths(){
		String nowdate = getNow("yyyyMMdd");
		String thisSchoolYear = getTermDate();
		if (nowdate.substring(0,4).equals(thisSchoolYear.substring(0,4))){
			return Integer.parseInt(nowdate.substring(4,6))-Integer.parseInt(thisSchoolYear.substring(4,6))+1;
		}else {
			return 	13-Integer.parseInt(thisSchoolYear.substring(4,6))+Integer.parseInt(nowdate.substring(4,6));
		}
	}

	/**
	 * 月份 for循环----数据处理
	 */
	public static String getTempDate(String tempDate,String givenDate){
		String givenYear = givenDate.substring(0,4);
		String givenMon = givenDate.substring(4, 6);
		if (givenMon.equals("01")||givenMon.equals("03")||givenMon.equals("05")||givenMon.equals("07")||givenMon.equals("08")||givenMon.equals("10")||givenMon.equals("12")){
			tempDate = DateUtil.strDateDayAdd(givenDate, 31 );
		}else if (givenMon.equals("02") && (Integer.parseInt(givenYear))%4 != 0){
			tempDate = DateUtil.strDateDayAdd(givenDate, 28 );
		}else if (givenMon.equals("02") && (Integer.parseInt(givenYear))%4 == 0){
			tempDate = DateUtil.strDateDayAdd(givenDate, 29 );
		}else {
			tempDate = DateUtil.strDateDayAdd(givenDate, 30 );
		}
		return tempDate;
	}
}
