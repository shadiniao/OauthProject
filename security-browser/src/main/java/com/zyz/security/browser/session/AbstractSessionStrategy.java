package com.zyz.security.browser.session;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyz.security.core.support.SimpleResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

/**
 * Created by yizhi on 2018-10-20.
 */
public class AbstractSessionStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String destinationUrl;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "非法的url地址");
        this.destinationUrl = invalidSessionUrl;
    }

    // session失效的处理方法, 如果是访问html就跳转到配置的session失效页面, 否则就以json返回
    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        if (createNewSession) {
            request.getSession();
        }

        String sourceUrl = request.getRequestURI();
        String targetUrl;

        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl;
            logger.info("session失效, 跳转到:" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        } else {
            Object result = buildResponseContent(request);
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(result));
        }
    }

    // 返回json的错误信息
    protected Object buildResponseContent(HttpServletRequest request) {
        String message = "session已失效";
        if (isConcurrency()) {
            message = message + ", 可能是并发登录导致的";
        }
        return new SimpleResponse(message);
    }

    // 是否为并发登录导致的
    protected boolean isConcurrency() {
        return false;
    }

    // 是否在session失效后创建新的session
    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}
