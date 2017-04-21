package cn.com.sinosoft.tbf.common.util.email;

public class EmailInfo {

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 收件人
	 */
	private String toEmail;

	/**
	 * 模板ID
	 */
	private String templateID;

	/**
	 * 附件
	 */
	private String extrafile;

	/**
	 * 附件名称
	 */
	private String filename;

	/**
	 * 邮件的接收者
	 */
	private String link;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getTemplateID() {
		return templateID;
	}
	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}
	public String getExtrafile() {
		return extrafile;
	}
	public void setExtrafile(String extrafile) {
		this.extrafile = extrafile;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}

}
