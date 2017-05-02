package cn.com.sinosoft.tbf.config.security;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.sinosoft.bomsmgr.model.common.LoginUserInfo;
import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;
import cn.com.sinosoft.tbf.common.security.annotation.mobileapi.HasRolesMApi;
import cn.com.sinosoft.tbf.common.util.http.ResponseUtil;
import cn.com.sinosoft.tbf.common.util.security.JwtHelper;
import cn.com.sinosoft.tbf.domain.common.APIResult;
import cn.com.sinosoft.tbf.domain.common.ResultCode;

/**
 * api token 认证拦截器
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月1日
 */
public class ApiTokenAuthInterceptor extends HandlerInterceptorAdapter {

	private Class<?> clazz;
	private Method method;
	private HttpServletResponse response;
	private HttpServletRequest request;

	@Autowired
	private JwtHelper jwtHelper;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean result = true;
		this.response = response;
		this.request = request;

		// 验证session token
		if (!validSession()) {
			// 验证jwt token
			result = result && validJWTToken();
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			clazz = handlerMethod.getBeanType();
			method = handlerMethod.getMethod();
			if (clazz != null && method != null) {

				// 处理角色权限过滤
				if (clazz.isAnnotationPresent(HasRolesMApi.class) || method.isAnnotationPresent(HasRolesMApi.class)) {
					result = result && validRolesMApi();
				}

			}

		}
		return result && super.preHandle(request, response, handler);
	}

	/**
	 * 验证session用户
	 *
	 * @return
	 */
	private boolean validSession() {
		LoginUserInfo loginUserInfo = (LoginUserInfo) request.getSession()
				.getAttribute(CommonUserService.SESSION_NAME_USERINFO);
		return loginUserInfo == null ? false : true;
	}

	/**
	 * 验证jwt token
	 *
	 * @return
	 */
	private boolean validJWTToken() {
		String auth = jwtHelper.getJWTToken();
		if (jwtHelper.parseJWT(auth) != null) {
			return true;
		} else {
			createUnLoginResponse();
			return false;
		}
	}

	/**
	 * 处理角色过滤注解<br/>
	 * 只要满足任意一个角色即可验证通过
	 *
	 * @return
	 */
	private boolean validRolesMApi() {
		boolean ret = false;
		List<AppRoles> roles = new ArrayList<AppRoles>();
		if (clazz.isAnnotationPresent(HasRolesMApi.class)) {
			AppRoles[] clazzRoles = clazz.getAnnotation(HasRolesMApi.class).value();
			Collections.addAll(roles, clazzRoles);
		} else if (method.isAnnotationPresent(HasRolesMApi.class)) {
			AppRoles[] methodRoles = method.getAnnotation(HasRolesMApi.class).value();
			Collections.addAll(roles, methodRoles);
		}
		for (AppRoles role : roles) {
			if (role == AppRoles.TEST) {
				return true;
			}
		}
		if (ret == false) {
			createUnAuthResponse();
		}
		return ret;
	}

	/**
	 * 创建未授权返回信息
	 */
	private void createUnAuthResponse() {
		APIResult<Object> resultMsg = new APIResult<Object>(ResultCode.UNAUTHORIZED.getCode(),
				ResultCode.UNAUTHORIZED.getDesc());
		ResponseUtil.createApiResultJson(response, resultMsg);
	}

	/**
	 * 创建未登录返回信息
	 */
	private void createUnLoginResponse() {
		APIResult<Object> resultMsg = new APIResult<Object>(ResultCode.UNAUTHENTICATION.getCode(),
				ResultCode.UNAUTHENTICATION.getDesc());
		ResponseUtil.createApiResultJson(response, resultMsg);
	}

}
