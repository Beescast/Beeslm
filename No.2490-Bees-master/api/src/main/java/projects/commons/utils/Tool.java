package projects.commons.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import projects.commons.utils.json.JsonUtils;
@SuppressWarnings({ "unchecked", "rawtypes", "unused"})
public class Tool {

    /**
     * 根据数组值作为概率进行随机选择
     * 
     * @param rand 概率数组，格式为： 键名1 => 概率值1, 键名2 => 概率值2,
     * @param degree 精度倍数
     * @param except 忽略的键名
     * @return
     */
    public static int getProbabilityKey(Map<?, ?> rand) {
        return getProbabilityKey(rand, 100, 0);
    }

    private static int getProbabilityKey(Map<?, ?> rand, int degree, int except) {
        if (except != 0) {
            rand.remove(except);
        }
        List<String> keys = new ArrayList<String>();
        int total = 0;
        for (Entry<?, ?> entry : rand.entrySet()) {
            total += Integer.valueOf(entry.getValue().toString());
            keys.add(entry.getKey().toString());
        }
        total = total * degree;

        if (total == 0) {
            return 0;
        }

        // 随机概率
        int intRand = Math.abs(new Random().nextInt(total));

        int offset = 0;
        int result = 0;

        for (Entry<?, ?> entry : rand.entrySet()) {
            int value = Integer.valueOf(entry.getValue().toString()) * degree;
            if (intRand <= value + offset) {
                result = Integer.valueOf(entry.getKey().toString());
                return result;
            }
            offset += value;
        }

        return result;
    }

    public static int getProbabilityKeyTask(Map<?, ?> rand) {
        return getProbabilityKeyTask(rand, 100, 0);
    }

    private static int getProbabilityKeyTask(Map<?, ?> rand, int degree, int except) {
        if (except != 0) {
            rand.remove(except);
        }
        List<String> keys = new ArrayList<String>();
        int total = 0;
        for (Entry<?, ?> entry : rand.entrySet()) {
            total += Integer.valueOf(entry.getValue().toString());
            keys.add(entry.getKey().toString());
        }
        total = total / rand.size() * degree;

        if (total == 0) {
            return 0;
        }

        // 随机概率
        int intRand = Math.abs(new Random().nextInt(total));

        int offset = 0;
        int result = 0;

        int keysSize = keys.size();
        for (int j = 0; j < keysSize; j++) {
            int r = Math.abs(new Random().nextInt()) % (keys.size());
            String key = keys.get(r);
            keys.remove(r);
            for (Entry<?, ?> entry : rand.entrySet()) {
                if (key.equals(entry.getKey().toString())) {
                    int value = Integer.valueOf(entry.getValue().toString()) * degree;
                    if (intRand <= value + offset) {
                        result = Integer.valueOf(entry.getKey().toString());
                        return result;
                    }
                    offset += value;
                }
            }

        }

        return result;
    }

    /**
     * 根据数组值作为概率进行随机选择
     * 
     * @param rand 概率数组，格式为： 键名1 => 概率值1, 键名2 => 概率值2,
     * @param degree 精度倍数
     * @param except 忽略的键名
     * @return
     */
    
	@Deprecated
    private static int getProbabilityKey100(Map<?, ?> rand, int degree, int except) {
        if (except != 0) {
            rand.remove(except);
        }
        List<String> keys = new ArrayList<String>();
        int total = 0;
        for (Entry<?, ?> entry : rand.entrySet()) {
            total += Integer.valueOf(entry.getValue().toString());
        }
        if (total == 0) {
            return 0;
        }

        if (total <= 100) {
            for (Entry<?, ?> entry : rand.entrySet()) {
                int value = Integer.valueOf(entry.getValue().toString());
                int num = value;
                for (int i = 0; i < num; i++) {
                    keys.add(entry.getKey().toString());
                }
            }
        } else {
            total = total * degree;
            for (Entry<?, ?> entry : rand.entrySet()) {
                int value = Integer.valueOf(entry.getValue().toString()) * degree;
                int num = (int) ((double) value / (double) total * 100);
                for (int i = 0; i < num; i++) {
                    keys.add(entry.getKey().toString());
                }
            }
        }

        Collections.shuffle(keys);

        // 随机概率
        int r = Math.abs(new Random().nextInt()) % (keys.size());
        return Integer.valueOf(keys.get(r));
    }

    /**
     * 根据概率值返回是否成功
     * 
     * @param value 概率值 ( 百分比值，如：30 表示 30% ) degree : 精度倍数
     * @return
     */
    public static boolean probability(int value) {
        return probability(value, 100, 100);
    }

    /**
     * 根据概率值返回是否成功
     * 
     * @param value 概率值 ( 百分比值，如：30 表示 30% ) degree : 精度倍数
     * @return
     */
    private static boolean probability(int value, int base, int degree) {
        if (value <= 0)
            return false;
        value = value * degree;
        int rand = Math.abs(new Random().nextInt(degree * base) + 1);
        return (rand <= value);
    }

    /**
     * 将Java秒数转为Unix时间戳 GameFunction.parseToUnixDate(System.currentTimeMillis()); 相当于 php中time()
     * 
     * @param millis
     * @return
     */
    public static int parseToUnixDate(long millis) {
        String timeStr = String.valueOf(millis);
        String unixTimeStamp = timeStr;
        if (timeStr.length() > 10) {
            unixTimeStamp = String.valueOf(millis).substring(0, 10);
        }
        return Integer.parseInt(unixTimeStamp);
    }

    /**
     * 将Unix时间戳转为Java秒数
     * 
     * @param unixTimeStamp
     * @return
     */
    public static long parseToMillis(int unixTimeStamp) {
        return (long) unixTimeStamp * (long) 1000;
    }

    /**
     * 将Unix时间戳转为字符串
     * 
     * @param unixTimeStamp
     * @return
     */
    public static String parseToDateString(int unixTimeStamp) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date((long) unixTimeStamp
                * (long) 1000));
    }

    /**
     * 将时间戳转为年月日小时分
     * 
     * @param unixTimeStamp
     * @return
     * @author lain
     */
    public static String parseToDateHourString(int unixTimeStamp) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date((long) unixTimeStamp
                * (long) 1000));
    }

    /**
     * 将时间戳转换为yyyy-mm-dd字符串
     * 
     * @param unixTimeStamp
     * @return
     */
    public static String parseToDateYMDString(int unixTimeStamp) {
        return new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date((long) unixTimeStamp
                * (long) 1000));
    }

    /**
     * 将Unix时间戳转为Date类型
     * 
     * @param unixTimeStamp
     * @return
     */
    public static Date parseToDate(int unixTimeStamp) {
        Long timeStamp = new Long((long) unixTimeStamp) * 1000;
        return new java.util.Date(timeStamp);
    }

    /**
     * 将时间戳转换为日期格式
     * 
     * @param unixTimeStamp
     * @return
     * @author lain
     */
    public static Date parseToDate(long unixTimeStamp) {
        return new java.util.Date(unixTimeStamp);
    }

    /**
     * <p>
     * Date转换成“yyyy-MM-dd”时间格式
     * </p>
     * 
     * @param date Date格式的时间
     * @return “yyyy-MM-dd”时间格式
     */
    public static String dateToYMDStr(Date date) {
        if (date == null) {
            return null;
        }
        String str = new SimpleDateFormat("yyyy-MM-dd").format(date);
        return str;
    }

    /**
     * 将字符串日期转为Date类型
     * 
     * @param date
     * @return
     */
    public static Date parseToDate(String date) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date parseToDateDay(String date) {
        try {
            return new java.text.SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 日期转为字符串
     * 
     * @param date
     * @param fmt
     * @return
     */
    public static String formatDate(Date date, String fmt) {
        return new SimpleDateFormat(fmt).format(date);
    }

    /***
     * 
     * 把java.util.Date ==> yyyy-MM-dd HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static String formatDateDay(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 获取默认时间值 0000-00-00 00:00:00
     * 
     * @return
     */
    public static Date getDefaultDate() {
        return new Date(0);
    }

    /**
     * 将时间精确到yyyy-Mm-dd 补0 为HH:mm:ss
     * 
     * @param date
     * @return
     */
    public static Date getDayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到今天凌晨的时间. GameFunction.getToday().getTime() 相当于 php中 date('Y-m-d');
     * 
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

    /**
     * 得到明天凌晨的时间. GameFunction.getTomorrow().getTime() 相当于 php中 date('Y-m-d');
     * 
     * @return
     */
    public static Date getTomorrow() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    /**
     * 得到今日23：59 时间
     * 
     * @return
     */
    public static Date getTodayLast() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 得到某一天的凌晨时间
     * 
     * @param date
     * @return
     */
    public static Date getToday(Date date) {
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
     * 
     * @param date
     * @return
     */
    public static Date getTodayLast(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        return cal.getTime();
    }

    /**
     * 昨天当前时间
     * 
     * @return
     */
    public static Date getYesterday() {
        Date today = Calendar.getInstance().getTime();
        long t = today.getTime();
        Date date = new Date(t - 24 * 60 * 60 * 1000l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(sdf.format(date));
        } catch (ParseException e) {

        }
        return date;
    }

    // 得到几天前当天时间===================================================================
    public static Date getSomeDate(Date date, int dayNum) {
        Calendar cal = Calendar.getInstance();
        long DAY = 1000 * 3600 * 24;
        cal.setTimeInMillis(date.getTime() + DAY * (long) dayNum);
        return cal.getTime();
    }

    public static boolean exceedLength(String value, int length) {
        return value == null || "".equals(value.trim()) || "null".equals(value.trim()) || value.length() > length;
    }

    /**
     * 根据用户心跳时间获取用户登录状态 -就是原来的online_state
     * 
     * @return 1-在线 2-活跃 3-未上线
     */
    public static int getUserLoginState(Date heartbeat) {
        int state = 3;
        if (heartbeat != null) {
            Long tmpOpTime = (System.currentTimeMillis() - heartbeat.getTime()) / 1000;
            int opTime = tmpOpTime.intValue();
            // 在线
            if (600 >= opTime) {
                state = 1;
            } else if (600 < opTime && 3600 * 24 >= opTime) {
                state = 2;
            } else if (3600 * 24 < opTime) {
                state = 3;
            }
        }
        return state;
    }

    /**
     * 将中文转为unicode 及转回中文函数
     * 
     * @param gbString 源中文字符串
     * @return 转换后的unicode字符串
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int byteIndex = 0; byteIndex < utfBytes.length; byteIndex++) {
            String hexB = Integer.toHexString(utfBytes[byteIndex]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    /**
     * 将unicode字符串转为汉字
     * 
     * @param dataStr 源unicode字符串
     * @return 转换后的字符串
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    
	public static void printMapToLog(Map<String, Object> map, Logger log) {
        try {

            StringBuilder content = new StringBuilder("=====print map content:");
            Iterator ite = map.entrySet().iterator();
            while (ite.hasNext()) {
                Map.Entry<String, Object> entry = (Entry<String, Object>) ite.next();
                content.append(entry.getKey()).append("=").append(entry.getValue()).append(",");
            }
            log.info(content);
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public static void printListToLog(List list, Logger log) {
        try {
            StringBuilder content = new StringBuilder("=====print list content:");
            for (Object object : list) {
                content.append(object.toString()).append(",");
            }
            log.info(content);
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
    }

    /**
     * java中如何获取某个范围内的随机数 相当于php rand(a,b)
     * 
     * @param a
     * @param b
     * @return
     */
    public static int rand(int a, int b) {
        int temp = 0;
        try {
            if (a > b) {
                temp = new Random().nextInt(a - b);
                return temp + b;
            } else if (b > a) {
                temp = new Random().nextInt(b - a);
                return temp + a;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return temp + a;
    }

    /**
     * 判断目标ip是否为本机
     * 
     * @return
     * @throws UnknownHostException
     */
    public static boolean isLocal(String ip) {
        Set ips = getAllLocalAddress();
        if (ips.contains(ip)) {
            return true;
        }
        return false;
    }

    /**
     * 获得所有本机的 IP 地址
     * 
     * @return
     */
    private static Set getAllLocalAddress() {
        Set result = new HashSet();
        Enumeration enu = null;
        try {
            enu = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            return result;
        }
        while (enu.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) (enu.nextElement());
            Enumeration ias = ni.getInetAddresses();
            while (ias.hasMoreElements()) {
                InetAddress i = (InetAddress) (ias.nextElement());
                if (i.getHostAddress().indexOf(":") == -1) {// 外网IP
                    String addr = i.getHostAddress();
                    result.add(addr);
                }
            }
        }

        return result;
    }

    /**
     * @return 本机外网IP
     * @throws SocketException
     */
    public static String getRealIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException se) {
            se.printStackTrace();
        }
        if (netip != null && !"".equals(netip)) {
            return netip;
        } else {
            return localip;
        }
    }

    /**
     * @return 本机内网IP
     * @throws SocketException
     */
    public static String getLocalIp() {
        String localip = null;// 本地IP，如果没有配置外网IP则返回它
        String netip = null;// 外网IP

        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            boolean finded = false;// 是否找到外网IP
            while (netInterfaces.hasMoreElements() && !finded) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> address = ni.getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 内网IP
                        localip = ip.getHostAddress();
                        finded = true;
                        break;
                    } else if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
                        netip = ip.getHostAddress();
                    }
                }
            }
        } catch (SocketException se) {
            se.printStackTrace();
        }

        if (localip != null && !"".equals(localip)) {
            return localip;
        } else {
            return netip;
        }
    }

    public static String getRemoteIp(HttpServletRequest request) {
        // 登录日志
        String remoteAddress = request.getRemoteAddr();
        final String forwarded = request.getHeader("X-Forwarded-For");
        if (remoteAddress == null) {
            remoteAddress = forwarded;
        } else if (forwarded != null && !forwarded.isEmpty()) {
            remoteAddress = forwarded;
            // remoteAddress += ";" + forwarded;
        }
        return remoteAddress;
    }

    /**
     * 将秒数转换成HH:mm:ss的形式
     * 
     * @param seconds
     * @return
     * @author lain
     */
    public static String secondToHourTime(int seconds) {
        try {
            long ms = seconds * 1000;
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");// 初始化Formatter的转换格式。
            formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
            return formatter.format(ms);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean postRequest(String u) throws Exception {
        URL url = null;
        HttpURLConnection httpconn = null;
        try {
            int resCode = 0;
            url = new URL(u);
            httpconn = (HttpURLConnection) url.openConnection();
            httpconn.setInstanceFollowRedirects(false);
            httpconn.setRequestMethod("POST");
            httpconn.setReadTimeout(60000);
            httpconn.setConnectTimeout(60000);
            resCode = httpconn.getResponseCode();
            if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return true;
            } else {
                System.out.println(resCode + " : " + u);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("Read timed out : " + u);
        } catch (Exception e) {
            throw e;
        } finally {
            if (httpconn != null) {
                httpconn.disconnect();
                httpconn = null;
            }
            if (url != null) {
                url = null;
            }
        }
        return false;
    }

    /**
     * 获取当前时间前推/后推的日期
     * 
     * @param dayNums 正数-后推 负数-前推
     * @return
     * @author lain
     */
    public static Date getAppointDate(int dayNums) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, dayNums);
        return cal.getTime();
    }

    public static int getPercentRandomIndex(List<Integer> rate) {
        // 精度倍数
        int degree = 100;
        int degreeRateSum = 0;
        List<Integer> degreeRate = new ArrayList<>();
        try {
            int tmpDegreeRate;
            for (Integer tmpRate : rate) {
                tmpDegreeRate = tmpRate * degree;
                degreeRate.add(tmpDegreeRate);
                degreeRateSum += tmpDegreeRate;
            }

            int rand = Math.abs(new Random().nextInt(degreeRateSum) + 1);
            int rateSize = rate.size();
            // 上下边界

            List<Integer> borders = new ArrayList<>();
            for (int i = 0; i < rateSize; i++) {
                if (i == 0) {
                    borders.add(degreeRate.get(0));
                } else {
                    borders.add(borders.get(i - 1) + degreeRate.get(i));
                }

            }

            int border1 = 0;
            int border2 = 0;
            for (int i = 0; i < rateSize; i++) {
                if (i == 0) {
                    border1 = 0;
                } else {
                    border1 = borders.get(i - 1);
                }

                if (border1 < rand && rand <= borders.get(i)) {
                    return i;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static boolean isSameDay(Date date1, Date date2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        return c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR) && c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
                && c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH);
    }

    public static String proccessServerId(String serverid) {
        // int serverId=Integer.valueOf(serverid);
        // switch(serverId){
        // case 100001:return "t1";
        // case 100002:return "t2";
        // case 100003:return "t3";
        // default: return "s"+serverid;
        // }
        // return serverid==null?null:"s"+serverid;
        // 不对serverId做处理了，直接返回
        return serverid;
    }

    /**
     * Random draw a award from jackpot which each award in it has a drawing weight.
     * <p>
     * The drawing weight represents that the award has a probability to draw.
     * 
     * @param prize
     * @return
     */
    public static int drawAward(List<Map<String, Object>> prize) {
        int sum = 0;
        for (Map<String, Object> award : prize) {
            Object value = award.get("weight");
            int weight = 1;
            if (value != null) {
                weight = Integer.valueOf(value.toString());
            }
            sum += weight;
        }
        int rand = new Random(System.currentTimeMillis()).nextInt(sum);

        int idx = 0;
        int tmp = 0;
        for (int i = 0, size = prize.size(); i < size; i++) {
            Object value = prize.get(i).get("weight");
            int weight = 1;
            if (value != null) {
                weight = Integer.valueOf(value.toString());
            }
            tmp += weight;
            if (tmp >= rand) {
                idx = i;
                break;
            }
        }
        idx = idx >= prize.size() ? prize.size() - 1 : idx;
        return idx;
    }

    private final static char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 生成随即密码
     * 
     * @param pwd_len 生成的密码的总长度
     * @return 密码的字符串
     */
    public static String genRandomNum(int pwd_len) {
        // 35是因为数组是从0开始的，26个字母+10个数字
        final int maxNum = 36;
        int i; // 生成的随机数
        int count = 0; // 生成的密码的长度

        StringBuffer pwd = new StringBuffer("");
        Random r = new Random();
        while (count < pwd_len) {
            // 生成随机数，取绝对值，防止生成负数
            i = Math.abs(r.nextInt(maxNum)); // 生成的数最大为36-1
            if (i >= 0 && i < str.length) {
                pwd.append(str[i]);
                count++;
            }
        }
        return pwd.toString();
    }

    /**
     * 生成几位随机数
     * 
     * @param len
     * 
     * @return 随机数的字符串
     */
    public static String genRandom(int len) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(r.nextInt(10));
        }
        return sb.toString();
    }

    public static Long genByMs6Digit() {
        return Long.valueOf(System.currentTimeMillis() % 1000L * 1000L + new Random().nextInt(1000));
    }

    // 做三层Hash
    public static String hashImgDir() throws Exception {
        return RandomStringUtils.randomNumeric(4) + "/" + RandomStringUtils.randomNumeric(4) + "/"
                + RandomStringUtils.randomNumeric(4) + Tool.genByMs6Digit().longValue();
    }

    public static void printAuthLog(HttpServletRequest request, HttpServletResponse response, boolean echoPost,
            Logger log) {
        String url = request.getRequestURL().toString();

        String uri = request.getRequestURI();

        if (!StringUtils.isBlank(request.getServletContext().getContextPath())
                && !"/".equals(request.getServletContext().getContextPath())) {
            uri = uri.substring(request.getServletContext().getContextPath().length(), uri.length());
        }

        StringBuilder sbparam = new StringBuilder();

        if (request.getQueryString() != null && !"".equals(request.getQueryString())) {
            sbparam.append(request.getQueryString());
        }

        if (echoPost && "POST".equals(request.getMethod())) {
            Map<String, Object> params = new HashMap<>();
            try {
                Enumeration paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = (String) paramNames.nextElement();

                    String[] paramValues = request.getParameterValues(paramName);
                    if (paramValues.length == 1) {
                        String paramValue = paramValues[0];
                        if (paramValue.length() != 0) {
                            params.put(paramName, paramValue.trim());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            sbparam.append(" POST:" + JsonUtils.convertEntity2Json(params));
        }

        StringBuilder urlparam = new StringBuilder(url);
        if (sbparam.toString() != null && !"".equals(sbparam.toString())) {
            String[] sbParams = sbparam.toString().split("&");
            String userId = "";
            for (String sbp : sbParams) {
                if (sbp.startsWith("sessionKey=")) {
                    String sbpp = sbp.replace("sessionKey=", "");
                    String[] userIdp = sbpp.split("_");
                    if (userIdp.length == 2) {
                        try {
                            // 转义字符处理
                            userId = userIdp[1];
                        } catch (Exception e) {
                            log.error(e);
                        }
                    }
                }
            }

            if (StringUtils.isEmpty(userId)) {
                urlparam.append("?").append(sbparam.toString());
            } else {
                urlparam.append("?").append(sbparam.toString()).append(" ").append(userId);
            }
        }

        log.info(Tool.getRemoteIp(request) + " - " + request.getMethod() + " " + urlparam.toString());

    }

    public static Map<String, Object> getZLog(HttpServletRequest request, HttpServletResponse response, Logger log) {

        Map<String, Object> map = new HashMap<String, Object>();
        String url = request.getRequestURL().toString();

        map.put("url", url);

        String uri = request.getRequestURI();

        if (!StringUtils.isBlank(request.getServletContext().getContextPath())
                && !"/".equals(request.getServletContext().getContextPath())) {
            uri = uri.substring(request.getServletContext().getContextPath().length(), uri.length());
            map.put("uri", uri);
        }

        if (request.getQueryString() != null && !"".equals(request.getQueryString())) {
            map.put("queryString", request.getQueryString());
        }

        if ("POST".equals(request.getMethod())) {

            try {
                Enumeration paramNames = request.getParameterNames();
                while (paramNames.hasMoreElements()) {
                    String paramName = (String) paramNames.nextElement();

                    String[] paramValues = request.getParameterValues(paramName);
                    if (paramValues.length == 1) {
                        String paramValue = paramValues[0];
                        if (paramValue.length() != 0) {
                            map.put(paramName, paramValue.trim());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        map.put("ip", Tool.getRemoteIp(request));
        map.put("method", request.getMethod());

        return map;

    }

    public static void main(String[] args) throws IOException, Exception {
        System.out.println(ConvertUtils.decrypt("Y8qEM42g2%2B4%3D".replace("%26", "&").replace("%23", "#")
                .replace("%25", "%").replace("%3F", "?").replace("%2F", "/").replace("%2B", "+").replace("%3D", "=")));
    }
}