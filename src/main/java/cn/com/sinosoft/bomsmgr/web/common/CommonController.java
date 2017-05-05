package cn.com.sinosoft.bomsmgr.web.common;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;
import cn.com.sinosoft.tbf.common.util.excel.Table2Excel;

/**
 * 公共控制器
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
@Controller
public class CommonController {

	@Resource
	CommonUserService commonUserService;

	/**
	 * 进入首页
	 *
	 * @return
	 */
	@RequestMapping(value = {"/", "/index", "/index.html"})
	public String toIndex() {
		return "index";
	}

	/**
	 * 进入登录页面
	 *
	 * @return
	 */
	@RequestMapping("/login")
	public String toLogin() {
		return "login";
	}

	/**
	 * 导出简单excel
	 *
	 *
	 * @param response
	 * @param tableJson
	 * @param title
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@RequestMapping("exportSimpleTable")
	public void exportExcel(HttpServletResponse response, String tableJson,
			String title) {
		try {
			if (title == null || title.trim().equals("")) {
				title = "export";
			}
			response.setContentType("application/vnd.ms-excel");
			title = URLEncoder.encode(title, "GB2312");
			title = URLDecoder.decode(title, "ISO8859_1");
			response.setHeader("Content-disposition", "attachment;filename="
					+ title + ".xls");
			OutputStream ouputStream = response.getOutputStream();
			new Table2Excel().transJson2Excel(tableJson, ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
