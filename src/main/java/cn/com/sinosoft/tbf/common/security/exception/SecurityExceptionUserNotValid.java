package cn.com.sinosoft.tbf.common.security.exception;

import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * 用户认证失败异常
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月29日
 */
public class SecurityExceptionUserNotValid extends AuthenticationServiceException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7146853652853480771L;

	public SecurityExceptionUserNotValid(String message, Throwable cause) {
		super(message, cause);
	}

	public SecurityExceptionUserNotValid(String message) {
		super(message);
	}

}
