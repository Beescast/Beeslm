/**
 * Filename��ValidateUtil.java Created by: qgl Created on: 2008-12-20 ����12:04:16
 * Last modified by��$Author$ Last modified on: $Date$ Revision: $Revision$
 */
package projects.commons.utils;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import projects.commons.utils.date.DateUtils;

public class ValidateUtils {

    public static Log log = LogFactory.getLog(ValidateUtils.class);

    public ValidateUtils() {
    }
    /**
	 * 验证是否为int类型
	 * 
	 * @param n
	 * @return
	 */
    public static boolean isInt(String arg) {
        if (arg == null)
            return false;
        try {
            Integer.valueOf(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
	 * 验证是否为float类型
	 * 
	 * @param n
	 * @return
	 */
    public static boolean isFloat(String arg) {
        if (arg == null)
            return false;
        try {
            Float.valueOf(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isLong(String arg) {
        if (arg == null)
            return false;
        try {
            Long.valueOf(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    
    public static boolean isDouble(String arg) {
        if (arg == null)
            return false;
        try {
            Double.valueOf(arg);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isDateTime(String arg) {
        if (arg == null)
            return false;
        try {
            return DateUtils.toDateTime(arg) != null;
        } catch (IllegalArgumentException e) {

        }
        return false;
    }

    public static boolean isNull(String value) {
        return value == null || "".equals(value.trim())
                || "null".equals(value.trim());
    }

    @SuppressWarnings("rawtypes")
	public static boolean isNull(Object value) {
        if (value == null)
            return true;
        if (value instanceof String)
            return isNull((String) value);
        if(value instanceof List){
        	if (((List) value).size()==0) {
				return true;
			}else{
				return false;
			}
        	
        }
        else
            return false;
    }

    public static void notNull(Object object, String message) {
        if (object == null)
            throw new IllegalArgumentException(message);
        else
            return;
    }

    public static void notNull(Object object) {
        notNull(object,
                "[Assertion failed] - this argument is required; it must not null");
    }

    public static boolean exceedLength(String value, int length) {
        return value == null || "".equals(value.trim())
                || "null".equals(value.trim()) || value.length() > length;
    }
    
    public static boolean lessLength(String value, int length) {
        return value == null || "".equals(value.trim())
                || "null".equals(value.trim()) || value.length() < length;
    }

    public static boolean containHTML(String value) {
        return value.indexOf("<") >= 0 || value.indexOf(">") >= 0
                || value.indexOf("&") >= 0 || value.indexOf("\"") >= 0
                || value.indexOf("'") >= 0 || value.indexOf("\\") >= 0;
    }

    public static boolean contains(String array[], String value) {
        for (int i = 0; array != null && i < array.length; i++)
            if (array[i].equals(value))
                return true;

        return false;
    }

    public static boolean isEmail(String email) {
        if (isNull(email))
            return false;
        if (!allValidChars(email))
            return false;
        if (email.indexOf("@") < 1)
            return false;
        if (email.lastIndexOf(".") <= email.indexOf("@"))
            return false;
        if (email.indexOf("@") == email.length())
            return false;
        if (email.indexOf("..") >= 0)
            return false;
        return email.indexOf(".") != email.length();
    }
    
    public static boolean isMobile(String mobile) {
        if (isNull(mobile))
            return false;
        return Pattern.matches("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|17[0|6|7|8]|18[0-9])\\d{8}$", mobile);
    }
    /**
     * 
     *  Created on 2015年2月10日 
     * <p>Description:验证身份证</p>
     * @author:刘宝仲
     * @param idNumber
     * @return
     */
    public static boolean isIdNumber(String idNumber ){
        if (isNull(idNumber))
            return false;
        String reg="^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";
        if(Pattern.matches(reg, idNumber)){
            return true;
        }else{
            return false;
        }
    }

    public static boolean isIDCard(String value, String provinceCode,
            String birthday) {

        return false;
    }

    public static boolean allValidChars(String c) {
    	c = c.toLowerCase();
        boolean parsed = true;
        String validchars = "abcdefghijklmnopqrstuvwxyz0123456789@.-_";
        for (int i = 0; i < c.length(); i++) {
            char letter = c.charAt(i);
            if (validchars.indexOf(letter) != -1)
                continue;
            parsed = false;
            break;
        }

        return parsed;
    }
    public static void main(String[] args) {
        System.out.println(isIdNumber("320106198208290"));
    }
}