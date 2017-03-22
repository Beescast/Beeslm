package projects.commons.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 用来转化字符串的类,目前有以下功能 1.将字符串由GB2312编码转化为ISO编码 2.将字符串由ISO编码转化为GB编码
 * <p/>
 * 注意使用场合 1. Tomcat 的默认编码为ISO 2. Mysql存储为ISO编码 3. 在bean中的编码和平台相关
 * 
 */
@SuppressWarnings("restriction")
public class ConvertUtils {

	protected static Logger log = LoggerFactory.getLogger(ConvertUtils.class);
	private final static String DES = "DES";
    private final static String KEY = "24sf32dwiy";

	/**
	 * 将ISO8859_1字符串转化为gb2312编码 Tomcat 的默认编码为ISO8859_1
	 * 
	 * @param pStr
	 * @return String
	 */
	public static String toGB(String pStr) {
		if (pStr == null) {
			return null;
		}

		try {
			return new String(pStr.getBytes("ISO-8859-1"), "GB2312");
		} catch (UnsupportedEncodingException e) {
			log.error("转换ISO字符串编码为GB2312时出错" + e.getMessage());

			return pStr;
		}
	}

	/**
	 * 将GB2312字符串转化为ISO8859_1编码
	 * 
	 * @param pStr
	 * @return String
	 */
	public static String toISO(String pStr) {
		if (pStr == null) {
			return null;
		}

		try {
			return new String(pStr.getBytes("GB2312"), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			log.error("转换字符串编码为ISO8859_1时出错" + e.getMessage());

			return pStr;
		}
	}

	/**
	 * 将字符型转换成int
	 * 
	 * @param arg
	 * @return
	 */
	public static int toInt(String arg) {
		return Integer.valueOf(arg).intValue();
	}

	/**
	 * 转换成 float型
	 * 
	 * @param arg
	 * @return
	 */
	public static float toFloat(String arg) {
		return Float.valueOf(arg).floatValue();
	}

	/**
	 * 转换成long
	 * 
	 * @param arg
	 * @return
	 */
	public static long toLong(String arg) {
		return Long.valueOf(arg).longValue();
	}

	/**
	 * 转换成timestamp
	 * 
	 * @param arg
	 *            格式可以是
	 * @return
	 */
	public static Timestamp toDateTime(String arg) {
		Locale locale = Locale.ENGLISH;
		if (arg == null || "".equals(arg)) {
			return null;
		}
		Timestamp ret = null;
		try {
			// 格式为 yyyy-MM-dd
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					locale);
			ret = new Timestamp(sdf.parse(arg).getTime());
			return ret;
		} catch (ParseException e) {
			try {
				// 格式 yyyy-MM-dd HH:mm:ss
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm",
						locale);
				ret = new Timestamp(sdf.parse(arg).getTime());
				return ret;
			} catch (ParseException e1) {
				try {
					// 格式 yyyy-MM-dd HH:mm
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",
							locale);
					ret = new Timestamp(sdf.parse(arg).getTime());
					return ret;
				} catch (ParseException e2) {
					try {
						// 格式 yyyy-MM
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM",
								locale);
						ret = new Timestamp(sdf.parse(arg).getTime());
						return ret;
					} catch (ParseException e3) {
						try {
							// 默认的Date 输入格式
							SimpleDateFormat sdf = new SimpleDateFormat(
									"EEE MMM dd HH:mm:ss z yyyy", locale);
							return new Timestamp(sdf.parse(arg).getTime());
						} catch (Exception e4) {
							// 2009年05月11日 14点23分05秒 2009-05-22
							try {
								// 默认的Date 输入格式
								SimpleDateFormat sdf = new SimpleDateFormat(
										"yyyy年MM月dd日 HH点mm分ss秒", locale);
								return new Timestamp(sdf.parse(arg).getTime());
							} catch (Exception e5) {
								try {
									// 默认的Date 输入格式
									SimpleDateFormat sdf = new SimpleDateFormat(
											"yyyy年MM月dd日", locale);
									return new Timestamp(sdf.parse(arg)
											.getTime());
								} catch (Exception e6) {
									// throw new
									// IllegalArgumentException("参数非法:" + arg);、
									try {
										// 默认的Date 输入格式
										SimpleDateFormat sdf = new SimpleDateFormat(
												"yyyy年MM月", locale);
										return new Timestamp(sdf.parse(arg)
												.getTime());
									} catch (Exception e7) {
										e.printStackTrace();
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 转换成timestamp
	 * 
	 * @param arg
	 *            格式可以是
	 * @return
	 */
	public static Timestamp toDateTime(String arg, boolean defaultValue) {
		Timestamp time = toDateTime(arg);
		if (!defaultValue) {
			return time;
		}
		if (time == null) {
			return new Timestamp(System.currentTimeMillis());
		}
		return null;
	}

	public static Date toDate(String arg) {
		Timestamp time = toDateTime(arg);
		if (time == null) {
			return null;
		}
		return new Date(time.getTime());
	}

	/**
	 * 将字符串转换成固定长度的字符串 otFixString(3,5) 返回 00003
	 * 
	 * @return
	 */
	public static String toFixString(String number, int size) {
		String ret = "";
		String temp = number;
		if (temp.length() > size) {
			return temp;
		}
		for (int i = 0; i < (size - temp.length()); i++) {
			ret += "0";
		}

		return ret + temp;

	}

	/**
	 * 将数字转换成固定长度的字符串 otFixString(3,5) 返回 00003
	 * 
	 * @return
	 */
	public static String toFixString(int number, int size) {
		String ret = "";
		String temp = String.valueOf(number);
		if (temp.length() > size) {
			return temp;
		}
		for (int i = 0; i < (size - temp.length()); i++) {
			ret += "0";
		}

		return ret + temp;

	}

	/**
	 * 将数字转换成固定长度的字符串 otFixString(3,5) 返回 00003
	 * 
	 * @return
	 */
	public static String toFixString(long number, int size) {
		String ret = "";
		String temp = String.valueOf(number);
		if (temp.length() > size) {
			return temp;
		}
		for (int i = 0; i < (size - temp.length()); i++) {
			ret += "0";
		}
		return ret + temp;

	}

	/**
	 * 将字符串参数转换成Unicode编码 native2ascii Convert text to Unicode Latin-1.
	 * native2ascii [options] [inputfile [outputfile]]
	 * 
	 * @param theString
	 *            传入的参数
	 * @return
	 */
	public static String toUnicode(String theString) {
		int len = theString.length();
		boolean escapeSpace = false;
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
			case ' ':
				if (x == 0 || escapeSpace) {
					outBuffer.append('\\');
				}
				outBuffer.append(' ');
				break;
			case '\t':
				outBuffer.append('\\');
				outBuffer.append('t');
				break;
			case '\n':
				outBuffer.append('\\');
				outBuffer.append('n');
				break;
			case '\r':
				outBuffer.append('\\');
				outBuffer.append('r');
				break;
			case '\f':
				outBuffer.append('\\');
				outBuffer.append('f');
				break;
			case '=':
				// Fall through
			case ':':
				// Fall through
			case '#':
				// Fall through
			case '!':
				outBuffer.append('\\');
				outBuffer.append(aChar);
				break;
			default:
				if ((aChar < 0x0020) || (aChar > 0x007e)) {
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				} else {
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * 将Unicode 字符串 转换成本地字符串
	 * 
	 * @param unicode
	 * @return
	 */
	public static String Unicode2String(String unicode) {

		if (unicode == null) {
			return null;
		}

		char[] in = new char[unicode.length()];

		unicode.getChars(0, unicode.length(), in, 0);

		int off = 0;
		int len = in.length;
		char[] convtBuf = new char[200]; // 默认为200个字符

		if (convtBuf.length < len) {
			int newLen = len * 2;
			if (newLen < 0) {
				newLen = Integer.MAX_VALUE;
			}
			convtBuf = new char[newLen];
		}
		char aChar;
		char[] out = convtBuf;
		int outLen = 0;
		int end = off + len;

		while (off < end) {
			aChar = in[off++];
			if (aChar == '\\') {
				aChar = in[off++];
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = in[off++];
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException(
									"Malformed \\uxxxx encoding.");
						}
					}
					out[outLen++] = (char) value;
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					out[outLen++] = aChar;
				}
			} else {
				out[outLen++] = (char) aChar;
			}
		}
		return new String(out, 0, outLen);
	}

	/**
	 * 将字符转换成UTF8
	 * 
	 * @param arg
	 * @return
	 */
	public static String toUTF8(String arg) {

		byte[] b = arg.getBytes();
		char[] c = new char[b.length];
		for (int x = 0; x < b.length; x++) {
			c[x] = (char) (b[x] & 0x00FF);
		}

		return new String(c);

	}

	public static String toDateFormat(Timestamp datetime) {

		return toDateFormat(datetime, null);
	}

	public static String toDateFormat(Date datetime) {

		return toDateFormat(datetime, null);
	}

	public static String toDateFormat(Timestamp datetime, String pattern) {
		SimpleDateFormat sdf = null;
		if (datetime == null) {
			return null;
		}
		try {
			if (pattern == null) {
				pattern = "yyyy-MM-dd HH:ss";
			}
			sdf = new SimpleDateFormat(pattern,Locale.US);

		} catch (Exception e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss",Locale.US);
		}
		return sdf.format(datetime);
	}

	public static String toDateFormat(Date datetime, String pattern) {
		SimpleDateFormat sdf = null;
		if (datetime == null) {
			return null;
		}
		try {
			if (pattern == null) {
				pattern = "yyyy-MM-dd HH:ss";
			}
			sdf = new SimpleDateFormat(pattern, Locale.US);

		} catch (Exception e) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:ss", Locale.US);
		}
		return sdf.format(datetime);
	}

	/**
	 * 转换成SHA编码,主要用于加密运算
	 * 
	 * @param password
	 * @return
	 */
	public static String encodeSHA(String password) {
		String ret = password;
		try {

			MessageDigest digest = MessageDigest.getInstance("SHA");

			byte encodes[] = digest.digest(password.getBytes());

			// Render the result as a String of hexadecimal digits
			StringBuffer result = new StringBuffer();
			for (int i = 0; i < encodes.length; i++) {
				byte b1 = (byte) ((encodes[i] & 0xf0) >> 4);
				byte b2 = (byte) (encodes[i] & 0x0f);
				if (b1 < 10) {
					result.append((char) ('0' + b1));
				} else {
					result.append((char) ('A' + (b1 - 10)));
				}
				if (b2 < 10) {
					result.append((char) ('0' + b2));
				} else {
					result.append((char) ('A' + (b2 - 10)));
				}
			}
			ret = result.toString();
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 将文本转换成标准的html，便于显示
	 * 
	 * @param html
	 * @return
	 */
	public static String toPlainText(String html) {
		return toPlainText(html, false);
	}

	public static String toPlainText(String html, boolean convertBr) {
		if (ValidateUtils.isNull(html)) {
			return "";
		}
		// 标准得html字符
		html = html.replaceAll("&", "&amp;");
		html = html.replaceAll("<", "&lt;");
		html = html.replaceAll(">", "&gt;");
		html = html.replaceAll("\"", "&quot;");
		if (convertBr) {
			html = html.replaceAll("\r", "");
			html = html.replaceAll("\n", "<br>");
			html = html.replaceAll("\r\n", "<br>");
		} else {
			html = html.replaceAll("\r\n", "");
		}
		// 特殊字符,会引起JavaScript错误
		html = html.replaceAll("\t", ""); // 转义字符
		html = html.replaceAll("'", "‘"); // '
		html = html.replaceAll("\"", "“"); // "
		return html;
		// 以下方法会导致汉字变成UTF－8编码
		// String temp = StringEscapeUtils.escapeJavaScript(StringEscapeUtils
		// .escapeHtml(html));
		// if (convertBr) {
		// temp = temp.replaceAll("\\\\r", "");
		// temp = temp.replaceAll("\\\\n", "<br>");
		// } else {
		// temp = StringEscapeUtils.escapeJava(temp);
		// }
		// return temp;
	}

	/**
	 * 将回车转换成<br>
	 * 
	 * @param html
	 * @return
	 */
	public static String toBr(String html) {

		if (html != null && html.length() > 0) {
			String temp = html;
			temp = temp.replaceAll("\n", "<br>");
			temp = temp.replaceAll("\r", "");
			temp = temp.replaceAll("\r\n", "<br>");
			return temp;
		}
		return "";
	}

	public static String escape(String src) {
		int i;
		char j;
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length() * 6);
		for (i = 0; i < src.length(); i++) {
			j = src.charAt(i);
			if (Character.isDigit(j) || Character.isLowerCase(j)
					|| Character.isUpperCase(j)) {
				tmp.append(j);
			} else if (j < 256) {
				tmp.append("%");
				if (j < 16) {
					tmp.append("0");
				}
				tmp.append(Integer.toString(j, 16));
			} else {
				tmp.append("%u");
				tmp.append(Integer.toString(j, 16));
			}
		}
		return tmp.toString();
	}

	public static String unescape(String src) {
		StringBuffer tmp = new StringBuffer();
		tmp.ensureCapacity(src.length());
		int lastPos = 0, pos = 0;
		char ch;
		while (lastPos < src.length()) {
			pos = src.indexOf("%", lastPos);
			if (pos == lastPos) {
				if (src.charAt(pos + 1) == 'u') {
					ch = (char) Integer.parseInt(
							src.substring(pos + 2, pos + 6), 16);
					tmp.append(ch);
					lastPos = pos + 6;
				} else {
					ch = (char) Integer.parseInt(
							src.substring(pos + 1, pos + 3), 16);
					tmp.append(ch);
					lastPos = pos + 3;
				}
			} else {
				if (pos == -1) {
					tmp.append(src.substring(lastPos));
					lastPos = src.length();
				} else {
					tmp.append(src.substring(lastPos, pos));
					lastPos = pos;
				}
			}
		}
		return tmp.toString();
	}
	
	/**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
	public static String encrypt(String data) throws Exception {
        byte[] bt = encrypt(data.getBytes(), KEY.getBytes());
        String strs = new BASE64Encoder().encode(bt);
        return strs;
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println(encrypt("100023"));
    }
 
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws IOException
     * @throws Exception
     */
	public static String decrypt(String data) throws IOException,
            Exception {
        if (data == null)
            return null;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] buf = decoder.decodeBuffer(data);
        byte[] bt = decrypt(buf,KEY.getBytes());
        return new String(bt);
    }
	
	 /**
     * Description 根据键值进行加密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }
     
     
    /**
     * Description 根据键值进行解密
     * @param data
     * @param key  加密键byte数组
     * @return
     * @throws Exception
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // 生成一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
 
        // 从原始密钥数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
 
        // 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
 
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
 
        // 用密钥初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
 
        return cipher.doFinal(data);
    }

	/**
	 * 将blob类型转换成String类型
	 * 
	 * @param blob
	 * @return
	 */
	public static String blob2String(Blob blob) {
		try {
			InputStream inStream = blob.getBinaryStream();
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			int bytesRead = 0;
			byte[] buffer = new byte[8192];

			while ((bytesRead = inStream.read(buffer)) != -1) {
				outStream.write(buffer, 0, bytesRead);
			}

			inStream.close();
			outStream.close();

			return outStream.toString();
		} catch (Exception e) {
			log.error("blob2String error={}", e);
			return "读取发生错误!";
		}
	}

	public static String clob2String(Clob clob) {
		try {
			StringWriter writer = new StringWriter();
			Reader reader = clob.getCharacterStream();
			int bytesRead = 0;
			char[] buffer = new char[8192];

			while ((bytesRead = reader.read(buffer)) != -1) {
				writer.write(buffer, 0, bytesRead);
			}

			writer.close();
			reader.close();

			return writer.toString();
		} catch (Exception e) {
			log.error("clob2String error={}", e);
			return "读取发生错误!";
		}
	}

	/**
	 * 获得16进制编码
	 * 
	 * @param nibble
	 * @return
	 */
	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	/**
	 * A table of hex digits
	 */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	/**
	 * 隐藏Ip 202.101.303.202 => 202.*.*.202
	 * 
	 * @param ip
	 * @return
	 */
	public static String toMaskIp(String ip) {
		if (ValidateUtils.isNull(ip)) {
			return "";
		}
		return ip.replaceAll("(\\.\\d{1,}+\\.\\d{1,}+\\.)", ".*.*.");
	}

	private static LinkedHashMap<String, Integer> spellMap = null;

	/**
	 * 根据ASCII码到SpellMap中查找对应的拼音
	 * 
	 * @param ascii
	 *            int 字符对应的ASCII
	 * @return String 拼音,首先判断ASCII是否>0&<160,如果是返回对应的字符,
	 *         否则到SpellMap中查找,如果没有找到拼音,则返回null,如果找到则返回拼音.
	 */
	private static String getSpellByAscii(int ascii) {
		if (ascii > 0 && ascii < 160) { // 单字符
			return String.valueOf((char) ascii);
		}

		if (ascii < -20319 || ascii > -10247) { // 不知道的字符
			return null;
		}

		Set<String> keySet = spellMap.keySet();
		Iterator<String> it = keySet.iterator();

		String spell0 = null;

		String spell = null;

		int asciiRang0 = -20319;
		int asciiRang;
		while (it.hasNext()) {
			spell = (String) it.next();
			Object valObj = spellMap.get(spell);
			if (valObj instanceof Integer) {
				asciiRang = ((Integer) valObj).intValue();

				if (ascii >= asciiRang0 && ascii < asciiRang) { // 区间找到
					return (spell0 == null) ? spell : spell0;
				} else {
					spell0 = spell;
					asciiRang0 = asciiRang;
				}
			}
		}
		return null;
	}

	private static void spellPut(String spell, int ascii) {
		spellMap.put(spell, ascii);
	}

	private static void initialize() {
		spellPut("a", -20319);
		spellPut("ai", -20317);
		spellPut("an", -20304);
		spellPut("ang", -20295);
		spellPut("ao", -20292);
		spellPut("ba", -20283);
		spellPut("bai", -20265);
		spellPut("ban", -20257);
		spellPut("bang", -20242);
		spellPut("bao", -20230);
		spellPut("bei", -20051);
		spellPut("ben", -20036);
		spellPut("beng", -20032);
		spellPut("bi", -20026);
		spellPut("bian", -20002);
		spellPut("biao", -19990);
		spellPut("bie", -19986);
		spellPut("bin", -19982);
		spellPut("bing", -19976);
		spellPut("bo", -19805);
		spellPut("bu", -19784);
		spellPut("ca", -19775);
		spellPut("cai", -19774);
		spellPut("can", -19763);
		spellPut("cang", -19756);
		spellPut("cao", -19751);
		spellPut("ce", -19746);
		spellPut("ceng", -19741);
		spellPut("cha", -19739);
		spellPut("chai", -19728);
		spellPut("chan", -19725);
		spellPut("chang", -19715);
		spellPut("chao", -19540);
		spellPut("che", -19531);
		spellPut("chen", -19525);
		spellPut("cheng", -19515);
		spellPut("chi", -19500);
		spellPut("chong", -19484);
		spellPut("chou", -19479);
		spellPut("chu", -19467);
		spellPut("chuai", -19289);
		spellPut("chuan", -19288);
		spellPut("chuang", -19281);
		spellPut("chui", -19275);
		spellPut("chun", -19270);
		spellPut("chuo", -19263);
		spellPut("ci", -19261);
		spellPut("cong", -19249);
		spellPut("cou", -19243);
		spellPut("cu", -19242);
		spellPut("cuan", -19238);
		spellPut("cui", -19235);
		spellPut("cun", -19227);
		spellPut("cuo", -19224);
		spellPut("da", -19218);
		spellPut("dai", -19212);
		spellPut("dan", -19038);
		spellPut("dang", -19023);
		spellPut("dao", -19018);
		spellPut("de", -19006);
		spellPut("deng", -19003);
		spellPut("di", -18996);
		spellPut("dian", -18977);
		spellPut("diao", -18961);
		spellPut("die", -18952);
		spellPut("ding", -18783);
		spellPut("diu", -18774);
		spellPut("dong", -18773);
		spellPut("dou", -18763);
		spellPut("du", -18756);
		spellPut("duan", -18741);
		spellPut("dui", -18735);
		spellPut("dun", -18731);
		spellPut("duo", -18722);
		spellPut("e", -18710);
		spellPut("en", -18697);
		spellPut("er", -18696);
		spellPut("fa", -18526);
		spellPut("fan", -18518);
		spellPut("fang", -18501);
		spellPut("fei", -18490);
		spellPut("fen", -18478);
		spellPut("feng", -18463);
		spellPut("fo", -18448);
		spellPut("fou", -18447);
		spellPut("fu", -18446);
		spellPut("ga", -18239);
		spellPut("gai", -18237);
		spellPut("gan", -18231);
		spellPut("gang", -18220);
		spellPut("gao", -18211);
		spellPut("ge", -18201);
		spellPut("gei", -18184);
		spellPut("gen", -18183);
		spellPut("geng", -18181);
		spellPut("gong", -18012);
		spellPut("gou", -17997);
		spellPut("gu", -17988);
		spellPut("gua", -17970);
		spellPut("guai", -17964);
		spellPut("guan", -17961);
		spellPut("guang", -17950);
		spellPut("gui", -17947);
		spellPut("gun", -17931);
		spellPut("guo", -17928);
		spellPut("ha", -17922);
		spellPut("hai", -17759);
		spellPut("han", -17752);
		spellPut("hang", -17733);
		spellPut("hao", -17730);
		spellPut("he", -17721);
		spellPut("hei", -17703);
		spellPut("hen", -17701);
		spellPut("heng", -17697);
		spellPut("hong", -17692);
		spellPut("hou", -17683);
		spellPut("hu", -17676);
		spellPut("hua", -17496);
		spellPut("huai", -17487);
		spellPut("huan", -17482);
		spellPut("huang", -17468);
		spellPut("hui", -17454);
		spellPut("hun", -17433);
		spellPut("huo", -17427);
		spellPut("ji", -17417);
		spellPut("jia", -17202);
		spellPut("jian", -17185);
		spellPut("jiang", -16983);
		spellPut("jiao", -16970);
		spellPut("jie", -16942);
		spellPut("jin", -16915);
		spellPut("jing", -16733);
		spellPut("jiong", -16708);
		spellPut("jiu", -16706);
		spellPut("ju", -16689);
		spellPut("juan", -16664);
		spellPut("jue", -16657);
		spellPut("jun", -16647);
		spellPut("ka", -16474);
		spellPut("kai", -16470);
		spellPut("kan", -16465);
		spellPut("kang", -16459);
		spellPut("kao", -16452);
		spellPut("ke", -16448);
		spellPut("ken", -16433);
		spellPut("keng", -16429);
		spellPut("kong", -16427);
		spellPut("kou", -16423);
		spellPut("ku", -16419);
		spellPut("kua", -16412);
		spellPut("kuai", -16407);
		spellPut("kuan", -16403);
		spellPut("kuang", -16401);
		spellPut("kui", -16393);
		spellPut("kun", -16220);
		spellPut("kuo", -16216);
		spellPut("la", -16212);
		spellPut("lai", -16205);
		spellPut("lan", -16202);
		spellPut("lang", -16187);
		spellPut("lao", -16180);
		spellPut("le", -16171);
		spellPut("lei", -16169);
		spellPut("leng", -16158);
		spellPut("li", -16155);
		spellPut("lia", -15959);
		spellPut("lian", -15958);
		spellPut("liang", -15944);
		spellPut("liao", -15933);
		spellPut("lie", -15920);
		spellPut("lin", -15915);
		spellPut("ling", -15903);
		spellPut("liu", -15889);
		spellPut("long", -15878);
		spellPut("lou", -15707);
		spellPut("lu", -15701);
		spellPut("lv", -15681);
		spellPut("luan", -15667);
		spellPut("lue", -15661);
		spellPut("lun", -15659);
		spellPut("luo", -15652);
		spellPut("ma", -15640);
		spellPut("mai", -15631);
		spellPut("man", -15625);
		spellPut("mang", -15454);
		spellPut("mao", -15448);
		spellPut("me", -15436);
		spellPut("mei", -15435);
		spellPut("men", -15419);
		spellPut("meng", -15416);
		spellPut("mi", -15408);
		spellPut("mian", -15394);
		spellPut("miao", -15385);
		spellPut("mie", -15377);
		spellPut("min", -15375);
		spellPut("ming", -15369);
		spellPut("miu", -15363);
		spellPut("mo", -15362);
		spellPut("mou", -15183);
		spellPut("mu", -15180);
		spellPut("na", -15165);
		spellPut("nai", -15158);
		spellPut("nan", -15153);
		spellPut("nang", -15150);
		spellPut("nao", -15149);
		spellPut("ne", -15144);
		spellPut("nei", -15143);
		spellPut("nen", -15141);
		spellPut("neng", -15140);
		spellPut("ni", -15139);
		spellPut("nian", -15128);
		spellPut("niang", -15121);
		spellPut("niao", -15119);
		spellPut("nie", -15117);
		spellPut("nin", -15110);
		spellPut("ning", -15109);
		spellPut("niu", -14941);
		spellPut("nong", -14937);
		spellPut("nu", -14933);
		spellPut("nv", -14930);
		spellPut("nuan", -14929);
		spellPut("nue", -14928);
		spellPut("nuo", -14926);
		spellPut("o", -14922);
		spellPut("ou", -14921);
		spellPut("pa", -14914);
		spellPut("pai", -14908);
		spellPut("pan", -14902);
		spellPut("pang", -14894);
		spellPut("pao", -14889);
		spellPut("pei", -14882);
		spellPut("pen", -14873);
		spellPut("peng", -14871);
		spellPut("pi", -14857);
		spellPut("pian", -14678);
		spellPut("piao", -14674);
		spellPut("pie", -14670);
		spellPut("pin", -14668);
		spellPut("ping", -14663);
		spellPut("po", -14654);
		spellPut("pu", -14645);
		spellPut("qi", -14630);
		spellPut("qia", -14594);
		spellPut("qian", -14429);
		spellPut("qiang", -14407);
		spellPut("qiao", -14399);
		spellPut("qie", -14384);
		spellPut("qin", -14379);
		spellPut("qing", -14368);
		spellPut("qiong", -14355);
		spellPut("qiu", -14353);
		spellPut("qu", -14345);
		spellPut("quan", -14170);
		spellPut("que", -14159);
		spellPut("qun", -14151);
		spellPut("ran", -14149);
		spellPut("rang", -14145);
		spellPut("rao", -14140);
		spellPut("re", -14137);
		spellPut("ren", -14135);
		spellPut("reng", -14125);
		spellPut("ri", -14123);
		spellPut("rong", -14122);
		spellPut("rou", -14112);
		spellPut("ru", -14109);
		spellPut("ruan", -14099);
		spellPut("rui", -14097);
		spellPut("run", -14094);
		spellPut("ruo", -14092);
		spellPut("sa", -14090);
		spellPut("sai", -14087);
		spellPut("san", -14083);
		spellPut("sang", -13917);
		spellPut("sao", -13914);
		spellPut("se", -13910);
		spellPut("sen", -13907);
		spellPut("seng", -13906);
		spellPut("sha", -13905);
		spellPut("shai", -13896);
		spellPut("shan", -13894);
		spellPut("shang", -13878);
		spellPut("shao", -13870);
		spellPut("she", -13859);
		spellPut("shen", -13847);
		spellPut("sheng", -13831);
		spellPut("shi", -13658);
		spellPut("shou", -13611);
		spellPut("shu", -13601);
		spellPut("shua", -13406);
		spellPut("shuai", -13404);
		spellPut("shuan", -13400);
		spellPut("shuang", -13398);
		spellPut("shui", -13395);
		spellPut("shun", -13391);
		spellPut("shuo", -13387);
		spellPut("si", -13383);
		spellPut("song", -13367);
		spellPut("sou", -13359);
		spellPut("su", -13356);
		spellPut("suan", -13343);
		spellPut("sui", -13340);
		spellPut("sun", -13329);
		spellPut("suo", -13326);
		spellPut("ta", -13318);
		spellPut("tai", -13147);
		spellPut("tan", -13138);
		spellPut("tang", -13120);
		spellPut("tao", -13107);
		spellPut("te", -13096);
		spellPut("teng", -13095);
		spellPut("ti", -13091);
		spellPut("tian", -13076);
		spellPut("tiao", -13068);
		spellPut("tie", -13063);
		spellPut("ting", -13060);
		spellPut("tong", -12888);
		spellPut("tou", -12875);
		spellPut("tu", -12871);
		spellPut("tuan", -12860);
		spellPut("tui", -12858);
		spellPut("tun", -12852);
		spellPut("tuo", -12849);
		spellPut("wa", -12838);
		spellPut("wai", -12831);
		spellPut("wan", -12829);
		spellPut("wang", -12812);
		spellPut("wei", -12802);
		spellPut("wen", -12607);
		spellPut("weng", -12597);
		spellPut("wo", -12594);
		spellPut("wu", -12585);
		spellPut("xi", -12556);
		spellPut("xia", -12359);
		spellPut("xian", -12346);
		spellPut("xiang", -12320);
		spellPut("xiao", -12300);
		spellPut("xie", -12120);
		spellPut("xin", -12099);
		spellPut("xing", -12089);
		spellPut("xiong", -12074);
		spellPut("xiu", -12067);
		spellPut("xu", -12058);
		spellPut("xuan", -12039);
		spellPut("xue", -11867);
		spellPut("xun", -11861);
		spellPut("ya", -11847);
		spellPut("yan", -11831);
		spellPut("yang", -11798);
		spellPut("yao", -11781);
		spellPut("ye", -11604);
		spellPut("yi", -11589);
		spellPut("yin", -11536);
		spellPut("ying", -11358);
		spellPut("yo", -11340);
		spellPut("yong", -11339);
		spellPut("you", -11324);
		spellPut("yu", -11303);
		spellPut("yuan", -11097);
		spellPut("yue", -11077);
		spellPut("yun", -11067);
		spellPut("za", -11055);
		spellPut("zai", -11052);
		spellPut("zan", -11045);
		spellPut("zang", -11041);
		spellPut("zao", -11038);
		spellPut("ze", -11024);
		spellPut("zei", -11020);
		spellPut("zen", -11019);
		spellPut("zeng", -11018);
		spellPut("zha", -11014);
		spellPut("zhai", -10838);
		spellPut("zhan", -10832);
		spellPut("zhang", -10815);
		spellPut("zhao", -10800);
		spellPut("zhe", -10790);
		spellPut("zhen", -10780);
		spellPut("zheng", -10764);
		spellPut("zhi", -10587);
		spellPut("zhong", -10544);
		spellPut("zhou", -10533);
		spellPut("zhu", -10519);
		spellPut("zhua", -10331);
		spellPut("zhuai", -10329);
		spellPut("zhuan", -10328);
		spellPut("zhuang", -10322);
		spellPut("zhui", -10315);
		spellPut("zhun", -10309);
		spellPut("zhuo", -10307);
		spellPut("zi", -10296);
		spellPut("zong", -10281);
		spellPut("zou", -10274);
		spellPut("zu", -10270);
		spellPut("zuan", -10262);
		spellPut("zui", -10260);
		spellPut("zun", -10256);
		spellPut("zuo", -10254);
	}

	/**
	 * 获得单个汉字的Ascii.
	 * 
	 * @param cn
	 *            char 汉字字符
	 * @return int 错误返回 0,否则返回ascii
	 */
	private static int getCNAscii(char cn) {
		byte[] bytes = (String.valueOf(cn)).getBytes();
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // 错误
			return 0;
		}

		if (bytes.length == 1) { // 英文字符
			return bytes[0];
		}

		if (bytes.length == 2) { // 中文字符
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];

			int ascii = (256 * hightByte + lowByte) - 256 * 256;
			// System.out.println( "ASCII=" + ascii );
			return ascii;
		}
		return 0; // 错误
	}

	/**
	 * 返回字符串的全拼,是汉字转化为全拼,简体字试图转换成繁体字，其它字符不进行转换
	 * 
	 * @param cnStr
	 *            String 字符串
	 * @return String 转换成全拼后的字符串
	 */
	public static String getFullSpell(String cnStr) {
		if (spellMap == null) {
			spellMap = new LinkedHashMap<String, Integer>(400);
			initialize();
		}
		if (null == cnStr || "".equals(cnStr.trim())) {
			return cnStr;
		}
		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			// 该拼音算法只针对gb2312，所以对一些显示不出来的字符做转换
			if ('薇' == chars[i]) {
				retuBuf.append("wei");
				continue;
			}
			if ('芙' == chars[i]) {
				retuBuf.append("fu");
				continue;
			}
			//
			int ascii = getCNAscii(chars[i]);
			if (ascii == 0) { // 取ascii时出错
				retuBuf.append(chars[i]);
			} else {
				String spell = getSpellByAscii(ascii);

				if (spell != null) {
					ascii = getCNAscii(spell.charAt(0));
					if (ascii != 0) { // 取ascii时出错
						spell = getSpellByAscii(ascii);
					}
				}

				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell);
				} // end of if spell == null
			} // end of if ascii <= -20400
		} // end of for
		return retuBuf.toString();
	}
}