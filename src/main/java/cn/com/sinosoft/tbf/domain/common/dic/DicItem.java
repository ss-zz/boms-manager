package cn.com.sinosoft.tbf.domain.common.dic;

/**
 * 单条字典项
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年9月12日
 */
public class DicItem {

	/**
	 * 字典值
	 */
	private String code;

	/**
	 * 字典描述
	 */
	private String desc;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
