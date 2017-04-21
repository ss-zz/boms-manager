package cn.com.sinosoft.tbf.domain.common;

import java.util.Date;

/**
 * 日志信息
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年12月20日
 */
public class LogInfo {

	/**
	 * 日志id
	 */
	private Integer id;

	/**
	 * 日志标题
	 */
	private String title;

	/**
	 * 日志级别
	 */
	private String level;

	/**
	 * 日志详情
	 */
	private String detail;

	/**
	 * 日志类型
	 */
	private String type;

	/**
	 * 记录时间
	 */
	private Date createTime;

	/**
	 * 记录人
	 */
	private String createUser;

	public LogInfo() {
	}

	public LogInfo(Integer id, String title, String level, String detail,
			String type, Date createTime, String createUser) {
		super();
		this.id = id;
		this.title = title;
		this.level = level;
		this.detail = detail;
		this.type = type;
		this.createTime = createTime;
		this.createUser = createUser;
	}

	public LogInfo(String title, String level, String detail, String type) {
		super();
		this.title = title;
		this.level = level;
		this.detail = detail;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

}
