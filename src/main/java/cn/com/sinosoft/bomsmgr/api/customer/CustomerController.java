package cn.com.sinosoft.bomsmgr.api.customer;

import java.util.Map;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.sinosoft.bomsmgr.entity.TCustomer;
import cn.com.sinosoft.bomsmgr.model.customer.ParamsCustomerEdit;
import cn.com.sinosoft.bomsmgr.service.customer.CustomerService;
import cn.com.sinosoft.tbf.domain.common.APIResult;
import cn.com.sinosoft.tbf.domain.common.PageParam;
import cn.com.sinosoft.tbf.domain.common.PagingResult;
import cn.com.sinosoft.tbf.domain.common.ResultCode;

/**
 * 客户API
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	@Resource
	CustomerService customerService;

	/**
	 * 客户列表
	 *
	 * @param searchParams
	 *            查询参数
	 * @param pageParam
	 *            分页参数
	 * @return
	 */
	@GetMapping("list")
	@ResponseBody
	public APIResult<PagingResult<TCustomer>> getList(@RequestParam Map<String, Object> searchParams, PageParam pageParam) {
		PagingResult<TCustomer> result = customerService.getCustomerList(searchParams, pageParam);
		return new APIResult<PagingResult<TCustomer>>(result);
	}

	/**
	 * 添加客户
	 *
	 * @param customer
	 *            客户信息
	 * @return
	 */
	@PostMapping("add")
	public APIResult<Integer> addCustom(@Valid ParamsCustomerEdit customer, BindingResult errors) {
		if (errors.hasErrors()) {
			return new APIResult<Integer>(ResultCode.FAILURE.getCode(), errors);
		}
		return new APIResult<Integer>(customerService.addCustomer(customer), "客户添加成功", true);
	}

	/**
	 * 编辑客户
	 *
	 * @param customer
	 *            客户信息
	 * @return
	 */
	@PostMapping("edit")
	public APIResult<String> editCustom(@Valid ParamsCustomerEdit customer, BindingResult errors) {
		if (errors.hasErrors()) {
			return new APIResult<String>(ResultCode.FAILURE.getCode(), errors);
		}
		customerService.updateCustomer(customer);
		return new APIResult<String>(null, "客户编辑成功", true);
	}

	/**
	 * 删除客户
	 *
	 * @param ids
	 *            id列表（以,隔开）
	 * @return 影响数据条数
	 */
	@PostMapping("del")
	public APIResult<Integer> deleteArticle(@RequestParam(required = true) Integer[] ids) {
		return new APIResult<Integer>(customerService.deleteCustomer(ids), "客户删除成功", true);
	}

	/**
	 * 获取客户详情
	 *
	 * @param id
	 *            文档id
	 * @return
	 */
	@GetMapping("get/{id}")
	public APIResult<TCustomer> getArticle(@PathVariable("id") Integer id) {
		return new APIResult<TCustomer>(customerService.getCustomerDetail(id));
	}

}
