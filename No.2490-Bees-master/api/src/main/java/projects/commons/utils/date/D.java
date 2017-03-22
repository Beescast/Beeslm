package projects.commons.utils.date;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class D {

	public static Timestamp toDateTime(String arg) {
        Locale locale;
        Timestamp ret;
        locale = Locale.ENGLISH;
        if (arg == null || "".equals(arg))
            return null;
        ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    locale);
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                    locale);
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e1) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e2) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM", locale);
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e3) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(
                    "EEE MMM dd HH:mm:ss z yyyy", locale);
            return new Timestamp(sdf.parse(arg).getTime());
        } catch (Exception e4) {

        }
        throw new IllegalArgumentException("参数非法:" + arg);
    }

    public static Timestamp toDateTime(String arg, boolean defaultValue) {
        Timestamp ret;
        if (!defaultValue)
            return toDateTime(arg);
        if (arg == null || "".equals(arg))
            return new Timestamp(System.currentTimeMillis());
        ret = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e1) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            ret = new Timestamp(sdf.parse(arg).getTime());
            return ret;
        } catch (ParseException e2) {

        }
        return new Timestamp(System.currentTimeMillis());
    }

    public static Date toDate(String arg) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.parse(arg);
        } catch (ParseException e) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            return sdf.parse(arg);
        } catch (ParseException e1) {

        }
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(arg);
        } catch (ParseException e2) {

        }
        return new Date();
    }
    

    public static String toDateFormat(Timestamp datetime) {
        return toDateFormat(datetime, null);
    }

    public static String toDateFormat(Date datetime) {
        return toDateFormat(datetime, null);
    }

    public static String toDateFormat(Timestamp datetime, String pattern) {
        SimpleDateFormat sdf = null;
        if (datetime == null)
            return null;
        try {
            if (pattern == null)
                pattern = "yyyy-MM-dd HH:ss";
            sdf = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        }
        return sdf.format(datetime);
    }

    public static String toDateFormat(Date datetime, String pattern) {
        SimpleDateFormat sdf = null;
        if (datetime == null)
            return null;
        try {
            if (pattern == null)
                pattern = "yyyy-MM-dd HH:ss";
            sdf = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss");
        }
        return sdf.format(datetime);
    }

    /**
	 * 得到某一天的凌晨时间
	 * @param date
	 * @return
	 */
	public static Date getToday(Date date){
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
	}
	/**
	 * 得到某一天的23：59 时间
	 * @param date
	 * @return
	 */
	public static Date getTodayLast(Date date){
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
	}
	
	/**
	 * 得到今天凌晨的时间.
	 * GameFunction.getToday().getTime() 相当于 php中 date('Y-m-d');
	 * @return
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
}
