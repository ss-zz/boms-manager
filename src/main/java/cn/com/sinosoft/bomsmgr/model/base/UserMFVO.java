/**
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
package cn.com.sinosoft.bomsmgr.model.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.sinosoft.bomsmgr.entity.TAuthMenuFun;

/**
 * 用户所有菜单功能点
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
public class UserMFVO implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 6060998445522121643L;
	/**
	 * 所有菜单功能点
	 */
	private Map<String, MFTreeVO> mFTrees;
	private List<TAuthMenuFun> mfList;

	public Map<String, MFTreeVO> getmFTrees() {
		return mFTrees;
	}

	public void setmFTrees(Map<String, MFTreeVO> mFTrees) {
		this.mFTrees = mFTrees;
	}

	public List<TAuthMenuFun> getMfList() {
		return mfList;
	}

	public void setMfList(List<TAuthMenuFun> mfList) {
		this.mfList = mfList;
	}

	public UserMFVO() {
		this.mFTrees = new HashMap<String, MFTreeVO>();
	}

}
