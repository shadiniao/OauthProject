package com.zyz.security.core.properties;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
public class BrowserProperties {
    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }
}
