package com.zyz.security;

import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2018/10/13.
 *
 * @author zhangyizhi
 */
//@Component("connect/giteeConnected")
public class GiteeView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write("<h3>绑定成功xxx</h3>");
	}
}
