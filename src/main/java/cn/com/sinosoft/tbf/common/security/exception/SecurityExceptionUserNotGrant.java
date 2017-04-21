package cn.com.sinosoft.tbf.common.security.exception;

/**
 * 用户未授权异常
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月29日
 */
public class SecurityExceptionUserNotGrant extends RuntimeException {

	/** serialVersionUID */
	private static final long serialVersionUID = 7146853652853480771L;

	public SecurityExceptionUserNotGrant(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SecurityExceptionUserNotGrant(String message) {
		super(message);
	}

}
