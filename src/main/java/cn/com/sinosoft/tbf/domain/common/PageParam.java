/**
 * Copyright www.sinosoft.com.cn
 */
package cn.com.sinosoft.tbf.domain.common;

/**
 * 分页参数
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @since 2014-10-23
 */
public class PageParam {
	
	/**
	 * 最大每页数据条数
	 */
	public static Integer MAX_LIMIT = 100;

	/**
	 * 所需条数
	 */
	int rows;
	/**
	 * 当前页数
	 */
	int page;
	/**
	 * 总数
	 */
	Integer total;
	/**
	 * 起始条数
	 */
	int start;
	/**
	 * 总页数
	 */
	int totalPage;

	public PageParam() {
		this.page=1;
		this.rows=10;
	}

	public PageParam(int page, int rows) {
		this.page = page;
		if(rows > MAX_LIMIT){
			rows = MAX_LIMIT;
		}
		this.rows = rows;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		if(rows > MAX_LIMIT){
			rows = MAX_LIMIT;
		}
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

}
