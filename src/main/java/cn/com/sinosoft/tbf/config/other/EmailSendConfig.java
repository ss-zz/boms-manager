package cn.com.sinosoft.tbf.config.other;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 邮件发送者配置信息
 *
 * @author <a href="mainto:lichuang@sinosoft.com.cn">lichuang</a>
 * @since 2016-9-27
 */
@ConfigurationProperties(prefix="mail.server")
@Component
public class EmailSendConfig {
	/**
	 * 邮箱主机(126.com)
	 */
	private String host;
	
	/**
	 * 邮箱用户名
	 */
	private String username;
	
	/**
	 * 邮箱密码
	 */
	private String password;
	
	/**
	 * 邮箱url
	 */
	private String fromemail;
	
	/**
	 * 是否发送邮件
	 */
	private boolean boolemail;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromemail() {
		return fromemail;
	}

	public void setFromemail(String fromemail) {
		this.fromemail = fromemail;
	}

	public boolean isBoolemail() {
		return boolemail;
	}

	public void setBoolemail(boolean boolemail) {
		this.boolemail = boolemail;
	}
	
}
