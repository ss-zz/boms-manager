package cn.com.sinosoft.bomsmgr.web.mgr;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理控制器
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
@Controller
public class MgrController {
	
	/**
	 * 进入后台管理页面
	 *
	 * @return
	 */
	@RequestMapping("/mgr.html")
	public String toMgr() {
		return "mgr";
	}

}
