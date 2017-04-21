/**
 * Copyright www.sinosoft.com.cn
 */
package cn.com.sinosoft.tbf.domain.common;

/**
 * 请求结果状态码：<br/>
 * 1-成功<br/>
 * 0-失败<br/>
 * 403-无权访问<br/>
 * 
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年3月16日
 */
public enum ResultCode {
	
	/**
	 * 请求结果状态码
	 */

	/**
	 * 请求成功
	 */
	SUCCESS("1", "成功"),
	
	/**
	 * 请求失败
	 */
	FAILURE("0", "失败"),
	
	/**
	 * 未授权
	 */
	UNAUTHORIZED("403", "无权访问"),
	
	/**
	 * 未认证
	 */
	UNAUTHENTICATION("401", "未登录");

	/**
	 * 编码
	 */
	private String code;
	
	/**
	 * 描述
	 */
	private String desc;

	private ResultCode(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * 获取编码.
	 *
	 * @return 编码值
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 获取描述.
	 *
	 * @return 描述内容
	 */
	public String getDesc() {
		return this.desc;
	}

}
