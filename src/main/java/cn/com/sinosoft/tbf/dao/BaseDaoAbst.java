package cn.com.sinosoft.tbf.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import cn.com.sinosoft.bomsmgr.model.common.LoginUserInfo;
import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;
import cn.com.sinosoft.tbf.domain.common.APIPageParam;
import cn.com.sinosoft.tbf.domain.common.PageParam;
import cn.com.sinosoft.tbf.domain.common.PagingResult;
import cn.com.sinosoft.tbf.domain.common.TbfPageBounds;
import cn.com.sinosoft.tbf.domain.common.dic.DicTransUtil;

/**
 * 抽象基础dao类-请不要直接注入此类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月9日
 */
public abstract class BaseDaoAbst {

	/**
	 * mybatis sql xml文件中用户信息key
	 */
	public static final String PARAMS_KEY_USERINFO = "USERINFO";

	public abstract SqlSessionTemplate getSqlSessionTemplate();
	public abstract CommonUserService getCommonUserService();

	/**
	 * 处理传入的查询参数、放入请求用户信息（仅处理map类型参数）
	 *
	 * @param params
	 */
	@SuppressWarnings("unchecked")
	private Object handleParams(Object params) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		if (params instanceof Map) {
			Map<String, Object> paramsMap = (Map<String, Object>) params;
			// 已存在-跳过
			if (paramsMap.get(PARAMS_KEY_USERINFO) != null) {
				return params;
			}
			CommonUserService commonUserService = getCommonUserService();
			if (commonUserService != null) {
				Map<String, Object> infos = new HashMap<String, Object>();
				LoginUserInfo userInfo = commonUserService.getRequestUser();
				if (userInfo == null)
					return params;

				// 用户id
				infos.put("userId", userInfo.getId());
				// 登录名
				infos.put("loginName", userInfo.getUserName());
				// 用户其他信息
				infos.put("user", userInfo.getUser());
				
				paramsMap.put(PARAMS_KEY_USERINFO, infos);
			}
		}
		return params;
	}

	/**
	 * 查询列表数据.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @return 列表数据
	 */
	public <T> List<T> queryList(String statement, Object params) {
		params = handleParams(params);
		List<T> items = getSqlSessionTemplate().selectList(statement, params);
		handleResult(items);
		return items == null ? new ArrayList<T>() : items;
	}

	/**
	 * 处理结果集
	 *
	 * @param items
	 */
	private <T> void handleResult(List<T> items) {
		if (items == null || items.size() == 0) {
			return;
		}
		for (T item : items) {
			if (item == null)
				continue;
			// 忽略map类型
			if (item instanceof Map || item instanceof HashMap) {
				return;
			}
			DicTransUtil.transDicPropertiesByAnno(item, item.getClass());
		}
	}

	/**
	 * 使用mybatis分页查询列表数据-分页.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @param pageBounds
	 *            分页参数
	 * @return 列表数据
	 */
	public <T> List<T> queryPageList(String statement, Object params,
			PageBounds pageBounds) {
		params = handleParams(params);

		List<T> items = getSqlSessionTemplate().selectList(statement, params,
				pageBounds);
		handleResult(items);

		return items;
	};

	/**
	 * 使用mybatis分页查询列表数据-分页.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @param pageBounds
	 *            分页参数
	 * @return 列表数据
	 */
	public <T> List<T> querySpePageList(String statement,
			Map<String, Object> params, PageBounds pageBounds) {
		if (params == null) {
			params = new HashMap<String, Object>();
		}
		params.put("offset", pageBounds.getOffset());
		params.put("limit", pageBounds.getLimit());

		List<T> items = getSqlSessionTemplate().selectList(statement, params);
		handleResult(items);

		return items;
	};

	/**
	 * 使用mybatis分页查询列表数据-分页.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @param rowBounds
	 *            分页参数
	 * @return 列表数据
	 */
	public List<Object> queryPageList(String statement, Object params,
			RowBounds rowBounds) {
		params = handleParams(params);
		return getSqlSessionTemplate().selectList(statement, params, rowBounds);
	};

	/**
	 * 插入数据.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @return 影响条数
	 */
	public int insert(String statement, Object params) {
		params = handleParams(params);
		return getSqlSessionTemplate().insert(statement, params);
	}

	/**
	 * 更新数据.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @return 影响条数
	 */
	public int update(String statement, Object params) {
		params = handleParams(params);
		return getSqlSessionTemplate().update(statement, params);
	}

	/**
	 * 删除数据.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @return 影响条数
	 */
	public int delete(String statement, Object params) {
		params = handleParams(params);
		return getSqlSessionTemplate().delete(statement, params);
	}

	/**
	 * 分页查询-使用mybatis实现.
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            参数
	 * @param pageParams
	 *            分页参数
	 * @return 分页查询结果
	 */
	public <T> PagingResult<T> pagingSearch(String statement, Object params,
			PageParam pageParams) {
		TbfPageBounds pageBounds = new TbfPageBounds(
				new RowBounds((pageParams.getPage() - 1) * pageParams.getRows(),
						pageParams.getRows()));
		pageBounds.setContainsTotalCount(true);
		PagingResult<T> result = new PagingResult<T>();

		// 查询总页数
		if (pageParams.getTotal() == null) {
			PageList<Object> pageCountItem = (PageList<Object>) queryPageList(
					statement, params, pageBounds);
			result.setTotal(pageCountItem.getPaginator().getTotalCount());
		} else {
			result.setTotal(pageParams.getTotal());
		}

		// 查询列表数据
		pageBounds.setContainsTotalCount(false);
		List<T> items = queryPageList(statement, params, pageBounds);
		result.setRows(items);

		return result;
	}

	/**
	 * 移动API数据分页查询-不查询总条目数
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            查询参数
	 * @param apiPageParams
	 *            分页参数
	 * @return
	 */
	public <T> PagingResult<T> pagingSearchMobile(String statement,
			Object params, APIPageParam apiPageParams) {
		TbfPageBounds pageBounds = new TbfPageBounds(new RowBounds(
				apiPageParams.getOffset(), apiPageParams.getLimit()));
		PagingResult<T> result = new PagingResult<T>();
		// 查询列表数据
		pageBounds.setContainsTotalCount(false);
		List<T> items = queryPageList(statement, params, pageBounds);
		result.setRows(items);
		return result;
	}

	/**
	 * 移动API特殊分页查询-不查询总条目数-分页sql在脚本文件中自定义编写
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            查询参数
	 * @param apiPageParams
	 *            分页参数
	 * @return
	 */
	public <T> PagingResult<T> pagingSearchSpe(String statement,
			Map<String, Object> params, APIPageParam apiPageParams) {
		TbfPageBounds pageBounds = new TbfPageBounds(new RowBounds(
				apiPageParams.getOffset(), apiPageParams.getLimit()));
		PagingResult<T> result = new PagingResult<T>();
		// 查询列表数据
		pageBounds.setContainsTotalCount(false);
		List<T> items = querySpePageList(statement, params, pageBounds);
		result.setRows(items);
		return result;
	}

	/**
	 * 移动API数据列表查询
	 *
	 * @param statement
	 *            唯一标识
	 * @param params
	 *            查询参数
	 * @return
	 */
	public <T> List<T> listSearch(String statement, Object params) {
		params = handleParams(params);
		return queryList(statement, params);
	}

}
