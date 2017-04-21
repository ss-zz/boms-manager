package cn.com.sinosoft.tbf.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 应用安全配置
 *
 * @author <a href="mainto:nytclizy@gmail.com">lizhiyong</a>
 * @since 2016年8月9日
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 自定义web认证
	 */
	@Autowired
	private AuthenticationProviderCustom authProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()// 禁用csrf
			.authorizeRequests()// 安全认证
				.antMatchers("/api/**").permitAll()// api 通过ApiTokenAuthInterceptor认证
				.anyRequest().authenticated() // 其它
			.and()
				.formLogin().loginPage("/login").permitAll()
			.and()
				.logout().permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/scripts/**", "/css/**", "/images/**");
	}

	/**
	 * 认证配置
	 *
	 * 
	 * @param auth
	 * @throws Exception
	 * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder)
	 */
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		// 自定义认证
		auth.authenticationProvider(authProvider);
		// 简单内存认证
		// auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
	}

}
