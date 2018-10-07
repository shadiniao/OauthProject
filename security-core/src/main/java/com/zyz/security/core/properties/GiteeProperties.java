package com.zyz.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GiteeProperties extends SocialProperties {
	private String providerId = "gitee";

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}
}
