package cn.com.sinosoft.bomsmgr.model.customer;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 客户添加实体类
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
public class ParamsCustomerEdit {

	
	/**
	 * 客户id，无需传入
	 */
	private Integer id;
	
	/**
	 * 客户名
	 */
	@NotBlank(message = "客户名不能为空")
	@Length(max = 40, message = "客户名最大长度为{max}")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
