package cn.com.sinosoft.bomsmgr.model.common;

import cn.com.sinosoft.bomsmgr.entity.TUser;
import cn.com.sinosoft.bomsmgr.model.base.MFTreeVO;

/**
 * 登录用户信息
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2017年4月21日
 */
public class LoginUserInfo {

	private String id;
	private String userName;
	private String passWord;
	private TUser user;
	private MFTreeVO mfTreeVo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public TUser getUser() {
		return user;
	}
	public void setUser(TUser user) {
		this.user = user;
	}
	public MFTreeVO getMfTreeVo() {
		return mfTreeVo;
	}
	public void setMfTreeVo(MFTreeVO mfTreeVo) {
		this.mfTreeVo = mfTreeVo;
	}

}
