package cn.com.sinosoft.tbf.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转换为日期
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月23日
 */
public class StringToDateConverter implements Converter<String, Date> {

	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	private static final String shortDateFormat = "yyyy-MM-dd";

	@Override
	public Date convert(String source) {
		if (StringUtil.isEmpty(source)) {
			return null;
		}
		source = source.trim();
		try {
			if (source.contains("-")) {
				SimpleDateFormat formatter;
				if (source.contains(":")) {
					formatter = new SimpleDateFormat(dateFormat);
				} else {
					formatter = new SimpleDateFormat(shortDateFormat);
				}
				Date dtDate = formatter.parse(source);
				return dtDate;
			} else if (source.matches("^\\d+$")) {
				Long lDate = new Long(source);
				return new Date(lDate);
			}
		} catch (Exception e) {
			throw new RuntimeException(
					String.format("转换 %s 为Date失败", source));
		}
		throw new RuntimeException(
				String.format("解析 %s 为Date失败", source));
	}

}
