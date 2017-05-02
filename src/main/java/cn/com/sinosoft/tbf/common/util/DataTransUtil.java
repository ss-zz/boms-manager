/**
 *
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-3-27
 */
package cn.com.sinosoft.tbf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * 数据类型转换工具类
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-3-27
 */
public class DataTransUtil {

	/**
	 * 转为Integer
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static Integer trans2Integer(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			return obj == null ? null : Integer.valueOf(String.valueOf(obj));
		}catch(Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为整数");
		}
	}

	/**
	 * 转为String
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static String trans2String(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			return obj == null ? null : String.valueOf(obj);
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为字符串");
		}
	}

	/**
	 * 转为Double
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static Double trans2Double(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			return obj == null ? null : Double.valueOf(String.valueOf(obj));
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为double");
		}
	}

	/**
	 * 转为Float
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static Float trans2Float(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			return obj == null ? null : Float.valueOf(String.valueOf(obj));
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为float");
		}
	}

	/**
	 * 转为Date <br />
	 * yyyy-MM-dd
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static Date trans2Date(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(obj == null) return null;
			return sdf.parse(String.valueOf(obj));
		}catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为日期");
		}
	}

	/**
	 * 转为Date <br />
	 * yyyy-MM-dd HH:mm:ss
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public static Date trans2DateTime(Map<String, Object> item, String fieldName){
		Object obj = item.get(fieldName);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(obj == null) return null;
			return sdf.parse(String.valueOf(obj));
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为时间戳");
		}
	}

	/**
	 * 转为Date <br />
	 *
	 *
	 *
	 * @param item
	 * @param fieldName
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 * @throws ParseException
	 */
	public static Date trans2Date(Map<String, Object> item, String fieldName, String pattern){
		Object obj = item.get(fieldName);
		try{
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			if(obj == null) return null;
			return sdf.parse(String.valueOf(obj));
		} catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(obj + "不能转换为日期" + pattern);
		}
	}

}
