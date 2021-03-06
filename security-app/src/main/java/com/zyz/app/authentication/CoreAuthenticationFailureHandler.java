package com.zyz.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyz.security.core.properties.LoginResponseType;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.support.SimpleResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
@Component
public class CoreAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ObjectMapper objectMapper; // spring mvc默认会注册一个

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException ex) throws IOException, ServletException {
        logger.info("登录失败");
        // 如果配置的是json, 就还是用老的处理方式
        if (securityProperties.getBrowser().getLoginResponseType().equals(LoginResponseType.JSON)) {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(ex.getMessage())));
        } else {
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
        }
    }
}
