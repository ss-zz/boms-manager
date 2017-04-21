package cn.com.sinosoft.bomsmgr.web.common;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;

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
	
}
