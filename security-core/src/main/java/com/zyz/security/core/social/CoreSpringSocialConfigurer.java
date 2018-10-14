package com.zyz.security.core.social;

import com.zyz.security.core.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

/**
 * 2018/9/21.
 *
 * @author zhangyizhi
 */
public class CoreSpringSocialConfigurer extends SpringSocialConfigurer {

	private String filterProcessesUrl;

	public CoreSpringSocialConfigurer(String filterProcessesUrl) {
		this.filterProcessesUrl = filterProcessesUrl;
	}

	@Override
	protected <T> T postProcess(T object) {
		SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);

		filter.setFilterProcessesUrl(filterProcessesUrl);

		return (T) filter;
	}
}
