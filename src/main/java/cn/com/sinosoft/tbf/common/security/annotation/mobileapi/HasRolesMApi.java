package cn.com.sinosoft.tbf.common.security.annotation.mobileapi;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import cn.com.sinosoft.tbf.config.security.AppRoles;

/**
 * 是否拥有指定角色-移动端api接口
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月1日
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Order(Ordered.HIGHEST_PRECEDENCE)
public @interface HasRolesMApi {

	/**
	 * 拥有的角色
	 *
	 * @return
	 */
	AppRoles[] value();

}
