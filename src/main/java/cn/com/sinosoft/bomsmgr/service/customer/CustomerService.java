package cn.com.sinosoft.bomsmgr.service.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.sinosoft.bomsmgr.entity.TCustomer;
import cn.com.sinosoft.bomsmgr.model.customer.ParamsCustomerEdit;
import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;
import cn.com.sinosoft.tbf.dao.BaseDao;
import cn.com.sinosoft.tbf.domain.common.PageParam;
import cn.com.sinosoft.tbf.domain.common.PagingResult;

/**
 * 客户服务类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
@Service
public class CustomerService {

	private static final String NAMESPACE_BASE = "cn.com.sinosoft.boms.customer.";

	@Resource
	BaseDao baseDao;
	@Resource
	CommonUserService commonUserService;

	/**
	 * 客户列表
	 * 
	 * @param searchParams
	 *            查询参数
	 * @param pageParam
	 *            分页参数
	 * @return
	 */
	public PagingResult<TCustomer> getCustomerList(Map<String, Object> searchParams, PageParam pageParam) {
		PagingResult<TCustomer> result = baseDao.pagingSearch(NAMESPACE_BASE + "customer-list", searchParams,
				pageParam);
		return result;
	}

	/**
	 * 添加客户
	 * 
	 * @param customer
	 *            客户信息
	 * @return 客户id
	 */
	public Integer addCustomer(ParamsCustomerEdit customer) {
		if (customer == null) {
			return null;
		}
		baseDao.insert(NAMESPACE_BASE + "customer-add", customer);
		return customer.getId();
	}

	/**
	 * 更新客户信息
	 * 
	 * @param customer
	 *            文档信息
	 */
	public void updateCustomer(ParamsCustomerEdit customer) {
		if (customer == null || customer.getId() == null)
			return;
		baseDao.update(NAMESPACE_BASE + "customer-edit", customer);
	}

	/**
	 * 删除客户
	 * 
	 * @param ids
	 *            id列表（以,隔开）
	 * @return 记录条数
	 */
	public int deleteCustomer(Integer[] ids) {
		if(ids == null || ids.length == 0) return 0;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("ids", ids);
		return baseDao.delete(NAMESPACE_BASE + "customer-del", params);
	}

	/**
	 * 根据id获取客户详细信息
	 * 
	 * @param id
	 *            客户id
	 * @return
	 */
	public TCustomer getCustomerDetail(Integer id) {
		if(id == null) return null;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<TCustomer> list = baseDao.queryList(NAMESPACE_BASE + "customer-list", params);
		return list.size() > 0 ? list.get(0) : null;
	}

}
