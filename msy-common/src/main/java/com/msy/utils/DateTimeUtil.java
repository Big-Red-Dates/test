package com.msy.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @description:
 * @date:
 */

public class DateTimeUtil
{
	public final static String SDFyyyyMM = "yyyy-MM";
	public final static String SDFyyyyMMdd = "yyyy-MM-dd";
	public final static String SDFyyyyMMddHH = "yyyy-MM-dd HH";
	public final static String SDFyyyyMMddHHmm = "yyyy-MM-dd HH:mm";
	public final static String SDFyyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss";
	public final static String SDFyyyyMMddHHmmssS = "yyyy-MM-dd HH:mm:ss.S";

	public final static long millisecondsPerMinute = 60L * 1000L;
	public final static long millisecondsPerHour = 60L * millisecondsPerMinute;
	public final static long millisecondsPerDay = 24L * millisecondsPerHour;
	public final static long millisecondsPerWeek = 7L * millisecondsPerDay;

	private static Calendar cal = Calendar.getInstance();

	public static String dateFormat(Timestamp d)
	{
		return d == null ? null : new SimpleDateFormat(SDFyyyyMMdd).format(d);
	}

	public static String DateTimeFormat(Date d)
	{
		return d == null ? null : new SimpleDateFormat(SDFyyyyMMddHHmmss).format(d);
	}

	public static String dateToString(Date date, String format)
	{
		if (date == null || format == null)
		{
			return null;
		}
		return new SimpleDateFormat(format).format(date);
	}

	public static Date stringToDate(String date, String format)
	{
		if (date == null || format == null || date == "")
		{
			return null;
		}
		try
		{
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException ex)
		{
			return null;
		}
	}

	public static Date parseStandardDate(String value)
	{
		Date date = null;
		int n = value.length();
		try
		{
			if (n >= "yyyy-MM-dd HH:mm:ss.S".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm:ss".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
			} else if (n == "yyyy-MM-dd HH:mm".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(value);
			} else if (n == "yyyy-MM-dd HH".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd HH").parse(value);
			} else if (n == "yyyy-MM-dd".length())
			{
				date = new SimpleDateFormat("yyyy-MM-dd").parse(value);
			} else if (n == "yyyy-MM".length())
			{
				date = new SimpleDateFormat("yyyy-MM").parse(value);
			}
		} catch (ParseException e)
		{
			Log log = LogFactory.getLog(DateTimeUtil.class.getName());
			log.warn("Can not parse [" + value + "] to java.util.Date");
		}
		return date;
	}

	/**
	 * 获取当前时间错 描述:
	 * 
	 * @return
	 */
	public static String getCurrentTimestampS()
	{
		final Calendar cal = Calendar.getInstance();
		final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");

		return df.format(cal.getTime());
	}

	/**
	 * 通过时间秒毫秒数判断两个时间的间隔
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws Exception 
	 */
	public static int differentDaysByMillisecond(Date smdate, Date bdate) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    smdate = sdf.parse(sdf.format(smdate));
	    bdate = sdf.parse(sdf.format(bdate));
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(smdate);
	    long time1 = cal.getTimeInMillis();
	    cal.setTime(bdate);
	    long time2 = cal.getTimeInMillis();
	    long between_days = (time2 - time1) / 86400000L;
	    int days = Integer.parseInt(String.valueOf(between_days));
	    days++;
	    return days;
	}
	
	/**
	 * 日期之前天数
	 * 
	 */
	public static int daysBetweenAdd1(java.util.Date smdate,
			java.util.Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 86400000L;
		int days = Integer.parseInt(String.valueOf(between_days));
		days = (days == 0) ? 1 : days;
		return days;
	} 

	/**
	 * 日期之前天数
	 * 
	 */
	public static int daysBetweenAdd(java.util.Date smdate,
			java.util.Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / 86400000L;
		int days = Integer.parseInt(String.valueOf(between_days));
		days ++;
		return days;
	} 
	/**
	 * 获取某月最大天数
	 * 
	 */
	public static int getMaxDayByYearMonth(int year, int month) {
		int day = 0;
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
				|| (month == 8) || (month == 10) || (month == 12))
			day = 31;
		else if ((month == 4) || (month == 6) || (month == 9) || (month == 11))
			day = 30;
		else if (month == 2) {
			if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0))
				day = 29;
			else {
				day = 28;
			}
		}
		return day;
	}

	public static int[] getTimeYMDArgs(String time)
	  {
	    int[] t = new int[3];
	    t[0] = Integer.parseInt(time.substring(0, 4));
	    t[1] = Integer.parseInt(time.substring(5, 7));
	    t[2] = Integer.parseInt(time.substring(8, 10));
	    return t;
	  }
	
	  public static List<java.util.Date> getPeriodsEndDateList(java.util.Date financePayDate, int expires)
	    throws Exception
	  {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String financePayDateStr = sdf.format(financePayDate);
	    int[] payDateArgs = getTimeYMDArgs(financePayDateStr);
	    int[] periodEdateArgs = new int[3];

	    periodEdateArgs[0] = payDateArgs[0];
	    periodEdateArgs[1] = payDateArgs[1];
	    List periodEdateList = new ArrayList();
	    for (int p = 1; p <= expires; p++)
	    {
	      if (payDateArgs[2] >= 2) {
	        periodEdateArgs[1] += 1;
	        payDateArgs[2] -= 1;
	      } else {
	        payDateArgs[1] += p - 1;
	        periodEdateArgs[2] = 31;
	      }

	      if (periodEdateArgs[1] > 12) {
	        periodEdateArgs[0] = (payDateArgs[0] + (periodEdateArgs[1] - 12) / 12 + 1);
	        periodEdateArgs[1] = ((periodEdateArgs[1] - 12) % 12);
	      }

	      if ((periodEdateArgs[2] == 31) && (
	        (periodEdateArgs[1] == 4) || (periodEdateArgs[1] == 6) || (periodEdateArgs[1] == 9) || 
	        (periodEdateArgs[1] == 11))) {
	        periodEdateArgs[2] = 30;
	      }

	      Calendar calEdate = Calendar.getInstance();
	      calEdate.set(periodEdateArgs[0], periodEdateArgs[1] - 1, 1);

	      if ((periodEdateArgs[1] == 2) && (periodEdateArgs[2] >= 29)) {
	        int sumDaysOfFeb = calEdate.getActualMaximum(5);
	        periodEdateArgs[2] = sumDaysOfFeb;
	      }
	      calEdate.set(5, periodEdateArgs[2]);
	      java.util.Date periodEdate = calEdate.getTime();
	      periodEdateList.add(periodEdate);
	    }
	    return periodEdateList;
	  }
	  
	  public static java.util.Date getDateNextDayT(java.util.Date date, int num)
	  {
	    Calendar cla = Calendar.getInstance();
	    cla.setTime(date);
	    cla.add(6, num);
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(cla.getTime());
	    return getStrToDate(dateString);
	  }
	  
	  public static java.util.Date getStrToDate(String time)
	  {
	    java.sql.Date sd = java.sql.Date.valueOf(time);
	    java.util.Date curDate = new java.util.Date(sd.getTime());
	    return curDate;
	  }
	/**
	  * 得到某年某月的第一天
	  * 
	  * @param date
	  * @return Date
	  */
	 public static Date getFirstDayOfMonth(Date date) {
	 
	  Calendar cal = Calendar.getInstance();
	 
	  cal.set(Calendar.YEAR, date.getYear());
	 
	  cal.set(Calendar.MONTH, date.getMonth()-1);
	 
	  cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));
	 
	  return cal.getTime();
	 }
	 
	 /**
	  * 得到某年某月的最后一天
	  * 
	  * @param date
	  * @return Date
	  */
	public static Date getLastDayOfMonth(Date date) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DATE, -1);
		
		return cal.getTime();

	}
	 
	 /*获取当前日期的下个月1号*/
	public static Date nextMonthFirstDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}
 
	/*获取当前日期的本月1号*/
	public static Date curMonthFirstDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return cal.getTime();
	}
	
	public static Date stringToDate(String time) throws ParseException{
	    SimpleDateFormat formatter;
	    time=time.trim() ;
	   
		if ((time.indexOf("/") > -1)) {
			formatter = new SimpleDateFormat("yyyy/MM/dd");
		} else if ((time.indexOf("-") > -1)) {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} else if ((time.indexOf(".") > -1)){
			time = time.replace(".", "");
			if(time.length()==6){
				String data = time.substring(0, 4);
				data += "0";
				data += time.substring(4, 5);
				data += "0";
				data += time.substring(5, 6);
				time = data.toString();
			}
            if(time.length()==7){
				
			}
			formatter = new SimpleDateFormat("yyyyMMdd");
		}else{
			formatter = new SimpleDateFormat ("yyyy/MM/dd");
		}

	    java.util.Date ctime = formatter.parse(time);

	    return ctime;
	}
	/**
	 * 获取当前时间错 描述:
	 * 
	 * @return
	 */
	public static String getCurrentTimestamp()
	{
		final Calendar cal = Calendar.getInstance();
		final DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

		return df.format(cal.getTime());
	}
	//按照给定的格式化字符串解析日期
	public static Date parseDate(String dateStr, String formatStr) throws ParseException { 
        Date date = null; 
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); 
        date = sdf.parse(dateStr); 
        return date; 
	}
	//从字符串中分析日期
	public static Date parseDate(String dateStr) throws ParseException { 
        Date date=null; 
        String[] dateArray = dateStr.split("\\D+");     
        //+防止多个非数字字符在一起时导致解析错误         
        int dateLen = dateArray.length; 
        int dateStrLen=dateStr.length(); 
        if(dateLen>0){ 
            if(dateLen==1&&dateStrLen>4){ 
                if(dateStrLen=="yyyyMMddHHmmss".length()){ 
                    //如果字符串长度为14位并且不包含其他非数字字符，则按照（yyyyMMddHHmmss）格式解析                     
                	date=parseDate(dateStr,"yyyyMMddHHmmss"); 
                }else if(dateStrLen=="yyyyMMddHHmm".length()){ 
                    date=parseDate(dateStr,"yyyyMMddHHmm"); 
                }else if(dateStrLen=="yyyyMMddHH".length()){ 
                    date=parseDate(dateStr,"yyyyMMddHH"); 
                }else if(dateStrLen=="yyyyMMdd".length()){ 
                    date=parseDate(dateStr,"yyyyMMdd"); 
                }else if(dateStrLen=="yyyyMM".length()){ 
                    date=parseDate(dateStr,"yyyyMM"); 
                } 
            }else{ 
                String fDateStr=dateArray[0]; 
                for(int i=1;i<dateLen;i++){ 
                    //左补齐是防止十位数省略的情况                     
                	fDateStr+=leftPad(dateArray[i],"0",2); 
                } 
                  
                if(dateStr.trim().matches("^\\d{1,2}:\\d{1,2}(:\\d{1,2})?$")){ 
                    //补充年月日3个字段                     
                	dateLen+=3; 
                    fDateStr=formatDate(new Date(),"yyyyMMdd")+fDateStr; 
                } 
                  
                date=parseDate(fDateStr,"yyyyMMddHHmmss".substring(0, (dateLen-1)*2+4)); 
            } 
        } 
          
        return date; 
    } 
	//按照给定的格式化字符串格式化日期
	public static String formatDate(Date date, String formatStr) { 
        SimpleDateFormat sdf = new SimpleDateFormat(formatStr); 
        return sdf.format(date); 
    } 
	
	//左补齐
	public static String leftPad(String str,String pad,int len){ 
        String newStr=(str==null?"":str); 
        while(newStr.length()<len){ 
            newStr=pad+newStr; 
        } 
        if(newStr.length()>len){ 
            newStr=newStr.substring(newStr.length()-len); 
        } 
        return newStr; 
    } 
	
	public static Date getTimeByHour(int hour) {

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);

        return calendar.getTime();

    }
}
