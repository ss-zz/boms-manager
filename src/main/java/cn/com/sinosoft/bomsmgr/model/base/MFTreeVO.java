/**
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
package cn.com.sinosoft.bomsmgr.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.com.sinosoft.bomsmgr.entity.TAuthMenuFun;

/**
 * 单个系统树状菜单功能点
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
public class MFTreeVO implements Cloneable, Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = -1128605302396538549L;
	private TAuthMenuFun mfVO;
	private List<MFTreeVO> children;

	public TAuthMenuFun getMfVO() {
		return mfVO;
	}

	public void setMfVO(TAuthMenuFun mfVO) {
		this.mfVO = mfVO;
	}

	public List<MFTreeVO> getChildren() {
		return children;
	}

	public void setChildren(List<MFTreeVO> children) {
		this.children = children;
	}

	public void addChild(MFTreeVO mfTreeVO) {
		if (this.children == null) {
			this.children = new ArrayList<MFTreeVO>();
		}
		this.children.add(mfTreeVO);
	}

	@Override
	public MFTreeVO clone() {
		MFTreeVO o = null;
		try {
			o = (MFTreeVO) super.clone();
		} catch (CloneNotSupportedException ex) {
			ex.printStackTrace();
		}
		return o;
	}
}
