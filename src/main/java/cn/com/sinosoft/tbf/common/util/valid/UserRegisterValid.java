package cn.com.sinosoft.tbf.common.util.valid;



/**
 * 注册信息验证
 *
 * @author <a href="mainto:lichuang@sinosoft.com.cn">lichuang</a>
 * @since 2016-10-27
 */
public class UserRegisterValid {
	
	/**
	 * 验证输入的字符串是否为纯数字
	 *
	 * @param number
	 * @return
	 */
	public boolean isNumber(String number){
		boolean result=false;
		if(number!=""||number!=null){
			result=number.matches("[0-9]+$");
		}
		return result;
	}
	
	/**
	 * 验证输入的字符串是否不含中文
	 *
	 * @param str
	 * @return
	 */
	public  boolean NotChinese(String str){
		boolean result=false;
		if(str!=""||str!=null){
			result=str.matches("^[a-zA-Z0-9_]+$");
		}
		return result;
	}
	
	/**
	 * 验证密码和确认密码是否一致
	 *
	 * @param password
	 * 		密码
	 * @param confirmpassword
	 * 		确认密码
	 * @return
	 */
	public boolean pwdsame(String password,String confirmpassword){
		if((password!=""||password!=null)&&(confirmpassword!=""||confirmpassword!=null)){
			if(password.equals(confirmpassword)){
				return true;
			}
			else{
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * 验证身份证号
	 *
	 * @param cardNo
	 * 		身份证号
	 * @param birthday
	 * 		出生日期
	 * @param sex
	 * 		性别
	 * @return
	 */
	public boolean CardNo(String cardNo,String birthday,int sex){
		if((cardNo!=""||cardNo!=null)&&(birthday!=""||birthday!=null)){
			if(cardNo.length()==18){
				String birth=cardNo.substring(6, 10)+"-"+cardNo.substring(10, 12)+"-"+cardNo.substring(12, 14);
				if(birthday.equals(birth)){
					int numsex=Integer.parseInt(cardNo.substring(16,17));
					if(numsex %2==0 &&sex==2){
						return true;
					}
					else if(numsex %2!=0 &&sex==1){
						return true;
					}
					else {
						return false;
					}
					
				}
				else {
					return false;
				}
			}
			else{
				return false;
			}
		}
		else {
			return false;
		}
	}
}
