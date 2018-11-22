package com.zyz.app;

import com.zyz.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.zyz.security.core.properties.SecurityConstants;
import com.zyz.security.core.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * Created by yizhi on 2018-10-21.
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler;

	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

	@Autowired
	private SpringSocialConfigurer coreSpringSocialConfigurer;

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.formLogin()
				.successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler)
				.loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
				.loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM);

		http.authorizeRequests() // 对请求授权, 下面的语句都会受影响
				.antMatchers(
						securityProperties.getBrowser().getLoginPage(),
						SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
						SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
						SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",
						securityProperties.getBrowser().getSignUpUrl(),
						"/user/regist",
						"/social/user",
						"/mylogout.html",
						securityProperties.getBrowser().getSession().getInvalidSessionUrl()
				)
				.permitAll()
				.anyRequest() // 任何请求
				.authenticated() // 都需要身份认证
				.and()
				.csrf().disable() // 暂时屏蔽csrf
				.apply(smsCodeAuthenticationSecurityConfig)
				.and()
//			.apply(validateCodeSecurityConfig)
//				.and()
				.apply(coreSpringSocialConfigurer);
	}
}
