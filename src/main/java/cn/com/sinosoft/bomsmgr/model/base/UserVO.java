/**
 *
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
package cn.com.sinosoft.bomsmgr.model.base;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author <a href="mailto:nytclizy@gmail.com">李志勇</a>
 * @date 2015-7-15
 */
public class UserVO implements Serializable{

	/** serialVersionUID*/
	private static final long serialVersionUID = 1L;
	private String id;
    private String createUser;
    private Date createTime;
    private String updateUser;
    private Date updateTime;
    private String loginName;
    private String name;
    private String passWord;
    private String userType;
    private String userLevel;
    private String orgCode;
    private String zoneCode;
    private String isUsed;
    private String email;
    private String sex;
    private String phone;

	private String orgDesc;
	private String zoneDesc;
	private UserMFVO userMFVO;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public UserMFVO getUserMFVO() {
		return userMFVO;
	}

	public void setUserMFVO(UserMFVO userMFVO) {
		this.userMFVO = userMFVO;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgDesc() {
		return orgDesc;
	}

	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}

	public String getZoneCode() {
		return zoneCode;
	}

	public void setZoneCode(String zoneCode) {
		this.zoneCode = zoneCode;
	}

	public String getZoneDesc() {
		return zoneDesc;
	}

	public void setZoneDesc(String zoneDesc) {
		this.zoneDesc = zoneDesc;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
