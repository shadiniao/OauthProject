package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.image.ImageCode;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
// 继承OncePerRequestFilter, 这是spring中的一个工具类, 保证filter每次请求只会被调用一次
public class SmsCodeFilter extends OncePerRequestFilter implements InitializingBean {

	private AuthenticationFailureHandler authenticationFailureHandler;

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	private SecurityProperties securityProperties;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	private Set<String> urls = new HashSet<>();


	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getUrls(), ",");

		for (String configUrl : configUrls) {
			urls.add(configUrl);
		}

		urls.add("/authentication/mobile");
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {

		boolean match = false;

		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				match = true;
				break;
			}
		}

		if (match) {
			try {
				validate(new ServletWebRequest((request)));
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}

	private void validate(ServletWebRequest request) throws ServletRequestBindingException {
		String sessionName = ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS";
		ValidateCode imageCode = (ValidateCode) sessionStrategy.getAttribute(request, sessionName);
		String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "smsCode");

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}

		if (imageCode == null) {
			throw new ValidateCodeException("验证码不存在");
		}

		if (imageCode.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionName);
			throw new ValidateCodeException("验证码已过期");
		}

		if (!StringUtils.equals(codeInRequest, imageCode.getCode())) {
			throw new ValidateCodeException("验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, sessionName);
	}

	public void setAuthenticationFailureHandler(
			AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	public void setSecurityProperties(SecurityProperties securityProperties) {
		this.securityProperties = securityProperties;
	}
}
