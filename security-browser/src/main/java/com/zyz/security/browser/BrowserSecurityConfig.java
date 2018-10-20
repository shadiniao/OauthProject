package com.zyz.security.browser;

import com.zyz.security.browser.session.MySessionInformationExpiredStrategy;
import com.zyz.security.core.authentication.AbstractChannelSecurityConfig;
import com.zyz.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zyz.security.core.properties.SecurityConstants;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.ValidateCodeSecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 2018/9/3.
 *
 * @author zhangyizhi
 */
@Configuration
public class BrowserSecurityConfig extends AbstractChannelSecurityConfig {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private ValidateCodeSecurityConfig validateCodeSecurityConfig;

	@Autowired
	private SpringSocialConfigurer coreSpringSocialConfigurer;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
		jdbcTokenRepository.setDataSource(dataSource);
//		jdbcTokenRepository.setCreateTableOnStartup(true);
		return jdbcTokenRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		applyPasswordAuthenticationConfig(http);

		http.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.userDetailsService(userDetailsService)
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.and()
			.sessionManagement()
				.invalidSessionUrl("/session/invalid")
				.maximumSessions(1)
				.maxSessionsPreventsLogin(true)
				.expiredSessionStrategy(new MySessionInformationExpiredStrategy())
				.and()
				.and()
			.authorizeRequests() // 对请求授权, 下面的语句都会受影响
				.antMatchers(
						securityProperties.getBrowser().getLoginPage(),
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
						securityProperties.getBrowser().getSignUpUrl(),
						"/user/regist",
						"/social/user",
						"/session/invalid"
				)
				.permitAll()
				.anyRequest() // 任何请求
				.authenticated() // 都需要身份认证
				.and()
			.csrf().disable() // 暂时屏蔽csrf
			.apply(smsCodeAuthenticationSecurityConfig)
				.and()
			.apply(validateCodeSecurityConfig)
				.and()
			.apply(coreSpringSocialConfigurer);
	}
}
