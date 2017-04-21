package cn.com.sinosoft.tbf.domain.common.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * jwt安全配置信息
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
@ConfigurationProperties(prefix = "audience")
@Component
public class Audience {
	
	/**
	 * 加密字符串
	 */
	private String base64Secret;
	
	/**
	 * 过期时间-秒
	 */
	private int expiresSecond;
	
	/**
	 * token名
	 */
	private String tokenName;
	
	/**
	 * 过滤的请求路径
	 */
	private List<String> urlPatterns = new ArrayList<String>();

	public String getBase64Secret() {
		return base64Secret;
	}

	public void setBase64Secret(String base64Secret) {
		this.base64Secret = base64Secret;
	}

	public int getExpiresSecond() {
		return expiresSecond;
	}

	public void setExpiresSecond(int expiresSecond) {
		this.expiresSecond = expiresSecond;
	}

	public String getTokenName() {
		return tokenName;
	}

	public void setTokenName(String tokenName) {
		this.tokenName = tokenName;
	}

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}
}
