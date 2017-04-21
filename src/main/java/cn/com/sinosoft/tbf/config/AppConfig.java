package cn.com.sinosoft.tbf.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 应用配置
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月31日
 */
@ConfigurationProperties(prefix = "app.config")
@Component
public class AppConfig {
	
	/**
	 * 文件上传路径
	 */
	private String uploadPath;
	
	/**
	 * 应用访问地址
	 */
	private String url;

	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
