/**
 * Copyright www.sinosoft.com.cn
 */
package cn.com.sinosoft.tbf.domain.common;


/**
 *
 * 分页查询原始sql.
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @since 2014-10-24
 */
public class PagingSrcSql {

	/**
	 * 原始sql
	 */
	String srcSql;
	/**
	 * 绑定参数
	 */
	Object[] values;

	public Object[] getValues() {
		return values;
	}

	public void setValues(Object[] values) {
		this.values = values;
	}

	public String getSrcSql() {
		return srcSql;
	}

	public void setSrcSql(String srcSql) {
		this.srcSql = srcSql;
	}

}
