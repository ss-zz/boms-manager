/**
 * Copyright www.sinosoft.com.cn
 */
package cn.com.sinosoft.tbf.domain.common;

import java.util.List;

/**
 * 分页参数查询结果
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @since 2014-10-23
 */
public class PagingResult<T> {
	
	/**
	 * 总数目
	 */
	int total;
	
	/**
	 * 列表内容
	 */
	
	List<T> rows;
	public int getTotal() {
		return total;
	}
	
	public void setTotal(int total) {
		this.total = total;
	}
	
	public List<T> getRows() {
		return rows;
	}
	
	public void setRows(List<T> rows) {
		this.rows = rows;
	}

}
