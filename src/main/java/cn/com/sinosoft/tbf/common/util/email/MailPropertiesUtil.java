package cn.com.sinosoft.tbf.common.util.email;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;



/**
 * 说明：获取系统properties配置文件信息工具类
 */
public class MailPropertiesUtil {
	
	/**
	 * 
	 * 说明：从配置文件url.properties中获取URL
	 * 
	 * @param key
	 * @return String
	 */
	public static String getUrlByCode(String key) {
		if (key == null || "".equals(key)) {
			return "";
		}
		InputStream inputStream = MailPropertiesUtil.class.getClassLoader().getResourceAsStream("mail/url.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return p.getProperty(key);
	}
	/**
	 * 
	 * 说明：从配置文件mail.properties中获取URL
	 * 
	 * @param key
	 * @return String
	 */
	public static String getMailInfoByCode(String key) {
		if (key == null || "".equals(key)) {
			return "";
		}
		InputStreamReader inputStream;
		Properties p = new Properties();
		try {
			inputStream = new InputStreamReader(MailPropertiesUtil.class.getClassLoader().getResourceAsStream("mail/mail.properties"), "UTF-8");
			
			try {
				p.load(inputStream);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
}
