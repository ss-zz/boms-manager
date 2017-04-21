package cn.com.sinosoft.tbf.common.util.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
public class MD5Util {

	/**
	 * md5加密
	 *
	 * @param value
	 * @return
	 */
	public static String digestMD5(String value) {
		/**
		 * 16进制字符
		 */
		char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F'};
		/**
		 * 转化成byte数组
		 */
		byte[] strTemp = value.getBytes();
		/**
		 * 得到md5实例，并加密
		 */
		MessageDigest mdTemp = null;
		try {
			mdTemp = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		mdTemp.update(strTemp);
		byte[] md = mdTemp.digest();
		/**
		 * 加密后的byte数组转化成16进制字符
		 */
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];// 取高位

			str[k++] = hexDigits[byte0 & 0xf];// 取低位
		}
		return new String(str);
	}

}
