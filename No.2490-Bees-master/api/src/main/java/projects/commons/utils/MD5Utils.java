package projects.commons.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.log4j.Logger;

/**
 * 计算MD5
 * 
 * @author qgl
 * 
 */
public class MD5Utils {
	private static Logger LOG_TIME_STAT = Logger.getLogger("timestat");
	/**
	 * 默认的密码字符串组合，apache校验下载的文件的正确性用的就是默认的这个组合
	 */
	protected static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		long begin = System.currentTimeMillis();

		File big = new File("E:/kankan/最强囍事__全集__3_160089_44382.xv");

		String md5 = getFileMD5String(big);

		long end = System.currentTimeMillis();
		System.out.println("md5:" + md5 + " time:" + ((end - begin) / 1000)
				+ "s");
	}

	/**
	 * 计算文件的MD5
	 * 
	 * @param fileName
	 *            文件的绝对路径
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	public static String getFileMD5String(String fileName) throws IOException, NoSuchAlgorithmException {
		File f = new File(fileName);
		return getFileMD5String(f);
	}

	/**
	 * 计算文件的MD5，重载方法 适用于上G大的文件
	 * 
	 * @param fileName
	 *            文件的绝对路径
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException 
	 */
	@SuppressWarnings("resource")
	public static String getFileMD5String(File file) throws IOException, NoSuchAlgorithmException {
		long begin = System.currentTimeMillis();
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		FileInputStream in = new FileInputStream(file);
		FileChannel ch = in.getChannel();
		MappedByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY,
				0, file.length());
		messagedigest.update(byteBuffer);
		byte[] bytes = messagedigest.digest();
		int m = 0;
		int n = bytes.length;
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		LOG_TIME_STAT.debug("MD5Time : "+(System.currentTimeMillis() - begin));
		return stringbuffer.toString();
	}
	
	public static String getFileMD5String(byte[] byteBuffer) throws IOException, NoSuchAlgorithmException {
		long begin = System.currentTimeMillis();
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(byteBuffer);
		byte[] bytes = messagedigest.digest();
		int m = 0;
		int n = bytes.length;
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		LOG_TIME_STAT.debug("MD5Time : "+(System.currentTimeMillis() - begin));
		return stringbuffer.toString();
	}

	public static String getMD5String(String s) throws IOException, NoSuchAlgorithmException {
		long begin = System.currentTimeMillis();
		byte[] byteBuffer = s.getBytes();
		MessageDigest messagedigest = MessageDigest.getInstance("MD5");
		messagedigest.update(byteBuffer);
		byte[] bytes = messagedigest.digest();
		int m = 0;
		int n = bytes.length;
		StringBuffer stringbuffer = new StringBuffer(2 * n);
		int k = m + n;
		for (int l = m; l < k; l++) {
			appendHexPair(bytes[l], stringbuffer);
		}
		LOG_TIME_STAT.debug("MD5Time : "+(System.currentTimeMillis() - begin));
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
		char c0 = hexDigits[(bt & 0xf0) >> 4];
		char c1 = hexDigits[bt & 0xf];
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static boolean checkPassword(String password, String md5PwdStr) throws IOException, NoSuchAlgorithmException {
		String s = getMD5String(password);
		return s.equals(md5PwdStr);
	}
	
	public static String getUUID(){
		 return UUID.randomUUID().toString();
	}
}
