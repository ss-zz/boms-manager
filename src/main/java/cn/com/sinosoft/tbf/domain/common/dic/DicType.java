package cn.com.sinosoft.tbf.domain.common.dic;

import java.util.List;

public class DicType {
	
	/**
	 * 字典类型
	 */
	private String dicType;
	
	/**
	 * 字典类型描述
	 */
	private String dicTypeDesc;
	
	/**
	 * 转换未命中默认值-默认""
	 */
	private String nullValue;
	
	/**
	 * 多个字典分隔符-默认","
	 */
	private String dupSp;
	
	/**
	 * 多个字典连接符-默认","
	 */
	private String dupJoin;
	
	/**
	 * 字典内容
	 */
	List<DicItem> dicItems;

	public String getDicType() {
		return dicType;
	}

	public void setDicType(String dicType) {
		this.dicType = dicType;
	}

	public String getDicTypeDesc() {
		return dicTypeDesc;
	}

	public void setDicTypeDesc(String dicTypeDesc) {
		this.dicTypeDesc = dicTypeDesc;
	}

	public String getNullValue() {
		return nullValue;
	}

	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}

	public List<DicItem> getDicItems() {
		return dicItems;
	}

	public void setDicItems(List<DicItem> dicItems) {
		this.dicItems = dicItems;
	}

	public String getDupSp() {
		return dupSp;
	}

	public void setDupSp(String dupSp) {
		this.dupSp = dupSp;
	}

	public String getDupJoin() {
		return dupJoin;
	}

	public void setDupJoin(String dupJoin) {
		this.dupJoin = dupJoin;
	}
	
}
