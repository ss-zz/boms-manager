/**
 *
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-10-15
 */
package cn.com.sinosoft.tbf.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 *	数据集处理工具类
 *
 * @author	<a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date	2015-10-15
 */
@Component
public class DataHandleUtil {

	/**
	 * 隐私数据标志
	 */
	public static final String FLAG_PRIVACYDATA = "*";

	/**
	 * 隐私数据处理-将指定字段变为*
	 *
	 * @param items 数据集
	 * @param fields 字段名
	 *
	 * @return 处理之后的数据集
	 */
	public List<Map<String, Object>> privacydataHandlerItem(List<Map<String, Object>> items,
			String[] fields) {
		List<Map<String, Object>> ret = new ArrayList<Map<String,Object>>();
		Iterator<Map<String,Object>> ite = items.iterator();
		while(ite.hasNext()){
			ret.add(privacydataHandler(ite.next(), fields));
		}
		return ret;
	}

	/**
	 * 单条隐私项数据处理-将制定字段变为*
	 *
	 * @param item 数据
	 * @param fields 字段
	 *
	 * @return 处理之后的数据
	 */
	public Map<String, Object> privacydataHandler(Map<String, Object> item,
			String[] fields) {
		if(item == null){
			return null;
		}
		for(String field: fields){
			item.put(field, handlerPrivacydata(String.valueOf(item.get(field))));
		}
		return item;
	}

	//处理隐私项数据-变为*
	private String handlerPrivacydata(String data) {
		if(data == null){
			return null;
		}else{
			return FLAG_PRIVACYDATA;
		}
	}

	/**
	 * 处理身份证号（传list）
	 * @param items
	 *            需要处理的对象
	 * @param field
	 *            字段名
	 */
	public List<Map<String, Object>> idCardHandlerItem(List<Map<String, Object>> items,
			String field) {
		List<Map<String, Object>> ret = new ArrayList<Map<String,Object>>();
		Iterator<Map<String,Object>> ite = items.iterator();
		while(ite.hasNext()){
			ret.add(idCardHandler(ite.next(), field));
		}
		return ret;
	}

	/**
	 * 处理身份证号码，加星-传Map对象
	 *
	 * @param idCard
	 *            身份证号
	 * @return 处理之后的身份证号 若返回值为null，说明处理失败,传入格式有问题
	 */
	public Map<String, Object> idCardHandler(Map<String, Object> item,
			String field) {
		if(item == null){
			return null;
		}
		item.put(field, handlerIdCard(String.valueOf(item.get(field))));
		return item;
	}

	/**
	 * 处理身份证号码，加星-传字符串
	 *
	 * @param idCard
	 *            身份证号
	 * @return 处理之后的身份证号 若返回值为null，说明处理失败,传入格式有问题
	 */
	public String idCardHandlerSimple(String idCard) {
		return handlerIdCard(idCard);
	}

	// 处理身份证公共方法
	private String handlerIdCard(String idCard) {
		if (idCard != null && !"".equals(idCard.trim())) {
			// 15位身份证
			if (idCard.length() == 15) {
				idCard = idCard.substring(0, 3) + "******"
						+ idCard.substring(12);
				// 18位身份证
			} else if (idCard.length() == 18) {
				idCard = idCard.substring(0, 3) + "************"
						+ idCard.substring(15);
			} else {
				//非法身份证位数
				return idCard;
			}
		} else {
			return "";
		}
		return idCard;
	}

	public static void main(String[] args) {
		List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
		Map<String, Object> item1 = new HashMap<String, Object>();
		item1.put("key1", "sdfsdf");
		item1.put("key2", "sdfsdf");
		item1.put("key3", 11);
		Map<String, Object> item2 = new HashMap<String, Object>();
		item2.put("key1", "sdfsdf1");
		item2.put("key2", "410182198812112254");
		item2.put("key3", 141);

		items.add(item1);
		items.add(item2);
		DataHandleUtil u = new DataHandleUtil();
		List<Map<String, Object>> newItems = u.privacydataHandlerItem(items, new String[]{"key1"});
		newItems = u.idCardHandlerItem(newItems, "key2");
		System.out.println(newItems);

	}

}
