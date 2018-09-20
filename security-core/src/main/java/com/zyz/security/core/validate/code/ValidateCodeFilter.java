package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.image.ImageCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
@Component("validateCodeFilter")
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Autowired
	private SecurityProperties securityProperties;

	private AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Autowired
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	private Map<String, ValidateCodeType> urlMap = new HashMap<>();

	@Override
	public void afterPropertiesSet() throws ServletException {
		super.afterPropertiesSet();
		urlMap.put("/authentication/form", ValidateCodeType.IMAGE);
		addUrlToMap(securityProperties.getCode().getImage().getUrls(), ValidateCodeType.IMAGE);

		urlMap.put("/authentication/mobile", ValidateCodeType.SMS);
		addUrlToMap(securityProperties.getCode().getSms().getUrls(), ValidateCodeType.SMS);
	}

	protected void addUrlToMap(String urlString, ValidateCodeType type) {
		String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");

		for (String configUrl : configUrls) {
			urlMap.put(configUrl, type);
		}
	}


	protected ValidateCodeType getValidateCodeType(HttpServletRequest request) {
		Set<String> urls = urlMap.keySet();
		for (String url : urls) {
			if (antPathMatcher.match(url, request.getRequestURI())) {
				return urlMap.get(url);
			}
		}

		return null;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
		ValidateCodeType validateCodeType = getValidateCodeType(request);

		if (validateCodeType != null) {
			logger.info("校验请求(" + request.getRequestURI() + ")中的验证码,验证码类型" + validateCodeType);
			try {
				ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(validateCodeType);
				validateCodeProcessor.validate(new ServletWebRequest(request));
				logger.info("验证码校验通过");
			} catch (ValidateCodeException e) {
				authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}

		filterChain.doFilter(request, response);
	}
}
