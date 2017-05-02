/**
 *
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2014-11-12
 */
package cn.com.sinosoft.bomsmgr.service.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.com.sinosoft.bomsmgr.entity.TAuthMenuFun;
import cn.com.sinosoft.bomsmgr.entity.TBaseDics;
import cn.com.sinosoft.bomsmgr.model.base.MFTreeVO;
import cn.com.sinosoft.tbf.common.util.StringUtil;
import cn.com.sinosoft.tbf.dao.BaseDao;

/**
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年5月2日
 */
@Service
public class CommonBaseService {

	public static final String NAMESPACE_BASE_DEFAULT = "cn.com.sinosoft.common.base.";

	@Resource
	BaseDao dao;

	/**
	 * 获取码表集合
	 *
	 * @param type
	 * @return
	 */
	@Cacheable(value = "common", key = "'getInitCodes' + #type")
	public List<List<TBaseDics>> getInitCodes(String type) {
		if (StringUtil.isEmpty(type)) {
			return new ArrayList<List<TBaseDics>>();
		} else {
			type = type.replace("|", ",");
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("codeTypes", type);
			List<TBaseDics> items = dao.queryList(NAMESPACE_BASE_DEFAULT + "code-list", params);
			return handleInitCodesResult(items);
		}
	}

	/**
	 * 处理码表结果集
	 *
	 * @param items
	 * @return
	 */
	private List<List<TBaseDics>> handleInitCodesResult(List<TBaseDics> items) {
		Map<String, Object> temp = new HashMap<String, Object>();
		Map<String, List<TBaseDics>> retTemp = new HashMap<String, List<TBaseDics>>();
		List<List<TBaseDics>> ret = new ArrayList<List<TBaseDics>>();
		for (TBaseDics item : items) {
			String codeType = item.getCodeType();
			if (temp.get(codeType) == null) {
				temp.put(codeType, true);
				retTemp.put(codeType, new ArrayList<TBaseDics>());
			}
			List<TBaseDics> list = retTemp.get(codeType);
			list.add(item);
		}
		for (String key : retTemp.keySet()) {
			ret.add(retTemp.get(key));
		}
		retTemp = null;
		temp = null;
		return ret;
	}

	/**
	 * 获取某类型码表
	 *
	 * @param type
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	@Cacheable(value = "common", key = "'getTypeCode' + #type")
	public List<TBaseDics> getTypeCode(String type) {
		if (StringUtil.isEmpty(type)) {
			return new ArrayList<TBaseDics>();
		} else {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("codeTypes", type);
			List<TBaseDics> items = dao.queryList(NAMESPACE_BASE_DEFAULT + "code-list", params);
			return items;
		}
	}

	/**
	 * 获取用户所有具有权限的菜单功能点
	 *
	 * @param userName
	 *            用户名
	 * @param type
	 *            类型：1-菜单、2-功能点
	 * @return
	 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
	 */
	public MFTreeVO getUserMF(String userName, String type) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userName", userName);
		params.put("type", type);
		List<TAuthMenuFun> items = dao.queryList(NAMESPACE_BASE_DEFAULT + "menu-fun-list", params);
		// 将菜单和功能点组装成一棵树
		return renderMenusAndFuns(items);
	}

	// 将菜单以及功能点融合成一棵树
	public MFTreeVO renderMenusAndFuns(List<TAuthMenuFun> mfs) {
		List<TAuthMenuFun> listMFVO = new ArrayList<TAuthMenuFun>();
		for (TAuthMenuFun mf : mfs) {
			listMFVO.add(mf);
		}
		// 获取0级菜单
		List<TAuthMenuFun> firstlevelMenus = getFirstlevelMenus(mfs);
		for (TAuthMenuFun mf : firstlevelMenus) {// 循环所有初级菜单
			return handleOneLevelMF(mf, mfs);
		}
		return null;
	}

	/**
	 *
	 * 获取初级菜单-父菜单为空或者level为0
	 *
	 */
	private List<TAuthMenuFun> getFirstlevelMenus(List<TAuthMenuFun> allMenus) {
		List<TAuthMenuFun> ret = new ArrayList<TAuthMenuFun>();
		for (TAuthMenuFun menu : allMenus) {
			if ("0".equals(menu.getLevel()) || menu.getParId() == null) {
				ret.add(menu);
			}
		}
		return ret;
	}

	/**
	 * 递归处理某一个菜单
	 */
	private MFTreeVO handleOneLevelMF(TAuthMenuFun mf, List<TAuthMenuFun> mfs) {
		MFTreeVO mfTreeVO = new MFTreeVO();
		mfTreeVO.setMfVO(mf);
		for (TAuthMenuFun item : mfs) {
			if (mf.getId() == item.getParId()) {
				MFTreeVO newMfTree = handleOneLevelMF(item, mfs);
				mfTreeVO.addChild(newMfTree);
			}
		}
		return mfTreeVO;
	}

}
