package cn.com.sinosoft.tbf.config.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * cas rest api配置
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月19日
 */
@ConfigurationProperties(prefix = "cas.server")
@Component
public class CasRestConfig {
	
	/**
	 * rest api url
	 */
	private String restUrl;
	
	/**
	 * service
	 */
	private String service;

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

}
