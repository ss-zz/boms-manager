package cn.com.sinosoft.tbf.common.util.http;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.com.sinosoft.tbf.domain.common.APIResult;
import cn.com.sinosoft.tbf.domain.common.ResultCode;

/**
 * response 工具类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月1日
 */
public class ResponseUtil {

	/**
	 * 创建json格式返回输出
	 *
	 * @param response
	 * @param result
	 */
	public static void createApiResultJson(HttpServletResponse response,
			APIResult<Object> result) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		ObjectMapper mapper = new ObjectMapper();
		if (result == null) {
			result = new APIResult<Object>(ResultCode.FAILURE.getCode(),
					ResultCode.FAILURE.getDesc());
		}
		try {
			response.getWriter().write(mapper.writeValueAsString(result));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
