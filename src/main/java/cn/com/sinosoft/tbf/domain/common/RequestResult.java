/**
 * Copyright www.sinosoft.com.cn
 */
package cn.com.sinosoft.tbf.domain.common;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * 请求结果
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @since 2014-10-29
 */
public class RequestResult<T> {
	
	/**
	 * 是否成功:1-成功,0-失败
	 */
	String success;
	/**
	 * 返回信息
	 */
	String msg;
	/**
	 * 返回的数据
	 */
	T data;
	
	/**
	 * 默认为 <br/>
	 * 	success: ResultCode.SUCCESS.getCode() <br/>
	 */
	public RequestResult(){
		this.success = ResultCode.SUCCESS.getCode();
	}
	
	/**
	 * 默认为 <br/>
	 * 	success: ResultCode.SUCCESS.getCode() <br/>
	 * 	msg: ResultCode.SUCCESS.getDesc() <br/>
	 */
	public RequestResult(String message){
		this.success = ResultCode.SUCCESS.getCode();
		this.msg = message;
	}
	
	/**
	 * 默认为 <br/>
	 * 	success: ResultCode.FAILURE.getCode() <br/>
	 */
	public RequestResult(Errors errors){
		this.success = ResultCode.FAILURE.getCode();
		this.msg = genMessageByErrors(errors);
	}
	
	/**
	 * 默认为 <br/>
	 * 	success: ResultCode.SUCCESS.getCode() <br/>
	 * 	msg: ResultCode.SUCCESS.getDesc() <br/>
	 */
	public RequestResult(T data){
		this.success = ResultCode.SUCCESS.getCode();
		this.msg = ResultCode.SUCCESS.getDesc();
		this.data = data;
	}
	
	private String genMessageByErrors(Errors errors){
		if(errors == null || !errors.hasErrors()){
			return "";
		}
		StringBuffer message = new StringBuffer();
		for(ObjectError error : errors.getAllErrors()){
			message.append(error.getDefaultMessage());
			message.append("\r");
		}
		return message.toString();
	}
	
	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
}
