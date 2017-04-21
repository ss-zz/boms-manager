package cn.com.sinosoft.tbf.config.security;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import cn.com.sinosoft.bomsmgr.entity.TUser;
import cn.com.sinosoft.bomsmgr.model.common.LoginUserInfo;
import cn.com.sinosoft.bomsmgr.service.common.CommonUserService;

/**
 * 自定义认证
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月9日
 */
@Component
public class AuthenticationProviderCustom implements AuthenticationProvider {

	@Autowired
	private CommonUserService userServcie;
	@Autowired
	HttpServletRequest request;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 用户名
		String userName = authentication.getName();
		// 密码
		String passWord = String.valueOf(authentication.getCredentials());
		// 验证
		TUser userInfo = userServcie.getUser(userName, passWord);
		if (userInfo != null) {
			// 用户被锁定
			if (!CommonUserService.USER_STATE_LOCKED.equals(userInfo.getState())) {
				throw new BadCredentialsException("用户已禁用");
			}
			
			LoginUserInfo loginUserInfo = new LoginUserInfo();
			loginUserInfo.setId(userInfo.getId());
			loginUserInfo.setUserName(userInfo.getUserName());
			loginUserInfo.setPassWord(userInfo.getPassWord());
			loginUserInfo.setUser(userInfo);
			
			request.getSession().setAttribute(CommonUserService.SESSION_NAME_USERINFO, loginUserInfo);

			// 授权
			Collection<? extends GrantedAuthority> authorities = null;
			return new UsernamePasswordAuthenticationToken(userName, passWord, authorities);
		} else {
			throw new BadCredentialsException("用户名或密码错误");
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
