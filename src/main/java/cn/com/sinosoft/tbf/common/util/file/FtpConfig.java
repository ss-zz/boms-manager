package cn.com.sinosoft.tbf.common.util.file;

/**
 * ftp 配置信息
 * 
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年1月5日
 */
public class FtpConfig {

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码
	 */
	private String passWord;

	/**
	 * ip地址
	 */
	private String addr;

	/**
	 * 端口号
	 */
	private int port;

	public FtpConfig() {
		this.userName = "admin";
		this.passWord = "123456";
		this.port = 21;
		this.addr = "192.168.1.111";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
