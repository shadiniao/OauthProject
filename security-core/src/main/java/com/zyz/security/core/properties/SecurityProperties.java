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

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }
}
