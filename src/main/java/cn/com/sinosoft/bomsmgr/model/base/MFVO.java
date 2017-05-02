/**
 *
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
package cn.com.sinosoft.bomsmgr.model.base;

import java.io.Serializable;

/**
 * 单条菜单功能点
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
public class MFVO implements Cloneable, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 6983950126681928759L;
	private Integer id;
	private String mfId;
	private String pmfId;
	private String mfName;
	private String mfLink;
	private String mfType;
	private Integer mfRank;
	private Integer mfLevel;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMfId() {
		return mfId;
	}
	public void setMfId(String mfId) {
		this.mfId = mfId;
	}
	public String getPmfId() {
		return pmfId;
	}
	public void setPmfId(String pmfId) {
		this.pmfId = pmfId;
	}
	public String getMfName() {
		return mfName;
	}
	public void setMfName(String mfName) {
		this.mfName = mfName;
	}
	public String getMfLink() {
		return mfLink;
	}
	public void setMfLink(String mfLink) {
		this.mfLink = mfLink;
	}
	public String getMfType() {
		return mfType;
	}
	public void setMfType(String mfType) {
		this.mfType = mfType;
	}
	public Integer getMfRank() {
		return mfRank;
	}
	public void setMfRank(Integer mfRank) {
		this.mfRank = mfRank;
	}
	public Integer getMfLevel() {
		return mfLevel;
	}
	public void setMfLevel(Integer mfLevel) {
		this.mfLevel = mfLevel;
	}
	@Override
	public MFVO clone() {
		MFVO o = null;
		try {
			o = (MFVO) super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return o;
	}
}
