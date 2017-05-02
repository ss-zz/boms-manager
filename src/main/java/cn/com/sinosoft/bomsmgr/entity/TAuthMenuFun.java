package cn.com.sinosoft.bomsmgr.entity;

public class TAuthMenuFun {

	private Integer id;
	private String mfKey;
	private String mfName;
	private Integer parId;
	private String type;
	private String url;
	private Integer level;
	private Integer rank;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getParId() {
		return parId;
	}
	public void setParId(Integer parId) {
		this.parId = parId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public String getMfKey() {
		return mfKey;
	}
	public void setMfKey(String mfKey) {
		this.mfKey = mfKey;
	}
	public String getMfName() {
		return mfName;
	}
	public void setMfName(String mfName) {
		this.mfName = mfName;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

}
