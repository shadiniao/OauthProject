package com.zyz.security.core.properties;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
public class BrowserProperties {
	private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
	private String signUpUrl = "/signup.html";
	private LoginResponseType loginResponseType = LoginResponseType.JSON;
	private int rememberMeSeconds = 3600;

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}

	public LoginResponseType getLoginResponseType() {
		return loginResponseType;
	}

	public void setLoginResponseType(LoginResponseType loginResponseType) {
		this.loginResponseType = loginResponseType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
