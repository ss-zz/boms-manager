package cn.com.sinosoft.tbf.common.util;

/**
 * 字符串工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月26日
 */
public class StringUtil {

	/**
	 * 字符串是否为空，包括null，以及空字符串"","  "...
	 *
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return (str == null || str.trim().equals("")) ? true : false;
	}
	
	/**
	 * 对象是否为空，包括null，以及空字符串"","  "...
	 *
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object str) {
		if(str == null) return true;
		if(str instanceof String){
			return String.valueOf(str).trim().equals("");
		}
		return false;
	}

}
