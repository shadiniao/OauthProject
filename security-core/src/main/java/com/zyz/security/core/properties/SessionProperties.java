package com.zyz.security.core.properties;

/**
 * Created by yizhi on 2018-10-20.
 */
public class SessionProperties {

    // 同一个用户在系统中的最大session数
    private int maximumSessions = 1;

    // 达到最大session时是否阻止新的登录
    private boolean maxSessionsPreventsLogin = true;

    // session失效时的跳转地址
    private String invalidSessionUrl = SecurityConstants.DEFAULT_INVALID_SESSION_URL;

    public String getInvalidSessionUrl() {
        return invalidSessionUrl;
    }

    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

    public int getMaximumSessions() {
        return maximumSessions;
    }

    public void setMaximumSessions(int maximumSessions) {
        this.maximumSessions = maximumSessions;
    }

    public boolean isMaxSessionsPreventsLogin() {
        return maxSessionsPreventsLogin;
    }

    public void setMaxSessionsPreventsLogin(boolean maxSessionsPreventsLogin) {
        this.maxSessionsPreventsLogin = maxSessionsPreventsLogin;
    }
}
