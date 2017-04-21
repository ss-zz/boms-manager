package cn.com.sinosoft.tbf.common.security.exception;

/**
 * 用户未登录异常
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月29日
 */
public class SecurityExceptionUserNotLogin extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7146853652853480771L;

	public SecurityExceptionUserNotLogin(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SecurityExceptionUserNotLogin(String message) {
		super(message);
	}

}
