package cn.com.sinosoft.tbf.config.app;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.sinosoft.tbf.common.ServiceException;
import cn.com.sinosoft.tbf.common.security.exception.SecurityExceptionUserNotGrant;
import cn.com.sinosoft.tbf.common.security.exception.SecurityExceptionUserNotValid;
import cn.com.sinosoft.tbf.domain.common.APIResult;
import cn.com.sinosoft.tbf.domain.common.ResultCode;

/**
 * 系统全局异常处理
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public APIResult<String> jsonErrorHandler(HttpServletRequest req,
			Exception e) throws Exception {
		APIResult<String> res = new APIResult<String>();
		res.setSuccess(ResultCode.FAILURE.getCode());
		if (e instanceof AuthenticationServiceException // 手动抛出特殊异常
				|| e instanceof ServiceException
				|| e instanceof SecurityExceptionUserNotValid) {
			res.setMsg(e.getMessage());
		} else if (e instanceof SecurityExceptionUserNotGrant) {// 未授权
			res.setMsg(ResultCode.UNAUTHORIZED.getDesc());
			res.setSuccess(ResultCode.UNAUTHORIZED.getCode());
		} else if (e instanceof SecurityExceptionUserNotGrant) {// 未登录
			res.setMsg(ResultCode.UNAUTHENTICATION.getDesc());
			res.setSuccess(ResultCode.UNAUTHENTICATION.getCode());
		} else if (e instanceof HttpRequestMethodNotSupportedException) { // 请求方法不支持
			res.setMsg("请求方法不支持");
		} else {
			e.printStackTrace();
			res.setMsg("请求失败");
		}
		return res;
	}

}
