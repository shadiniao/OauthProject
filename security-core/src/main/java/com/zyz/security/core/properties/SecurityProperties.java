package com.zyz.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
@ConfigurationProperties(prefix = "core.security")
	public class SecurityProperties {
	private BrowserProperties browser = new BrowserProperties();

	private ValidateCodeProperties code = new ValidateCodeProperties();

	private SocialProperties social = new SocialProperties();

	public SocialProperties getSocial() {
		return social;
	}

	public void setSocial(SocialProperties social) {
		this.social = social;
	}

	public ValidateCodeProperties getCode() {
		return code;
	}

	public void setCode(ValidateCodeProperties code) {
		this.code = code;
	}

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
}
