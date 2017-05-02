package cn.com.sinosoft.bomsmgr.api.base;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sinosoft.bomsmgr.model.base.MFTreeVO;
import cn.com.sinosoft.bomsmgr.model.common.LoginUserInfo;
import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;
import cn.com.sinosoft.tbf.domain.common.APIResult;

/**
 * 基础控制器
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年5月2日
 */
@RestController
@RequestMapping("/api/base")
public class BaseController {

	@Resource
	CommonUserService commonUserService;

	/**
	 * 获取我的菜单
	 *
	 * @return
	 */
	@GetMapping("getMyMenus")
	public APIResult<MFTreeVO> getList() {
		LoginUserInfo info = commonUserService.getRequestUser();
		return new APIResult<MFTreeVO>(info == null ? null : info.getMfTreeVo());
	}

}
