package cn.com.sinosoft.tbf.domain.common.dic;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 字典转换-字典字段请使用string类型
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月12日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
public @interface DicTrans {

	/**
	 * 字典类型
	 */
	String value();

	/**
	 * 原始属性-可选-默认为当前属性去除Desc，如：sexDesc原始属性为sex。
	 */
	String srcField() default "";

}
