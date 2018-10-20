package com.zyz.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import java.io.IOException;

import javax.servlet.ServletException;

// session失效的处理策略
public class MySessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
	@Override
	public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
		event.getResponse().setContentType("application/json;charset=utf-8");
		event.getResponse().getWriter().write("并发登录");
	}
}
