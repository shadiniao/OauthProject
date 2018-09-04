package com.zyz.security.browser.support;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
