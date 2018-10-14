package com.zyz.security.core.social;

import org.springframework.web.servlet.view.AbstractView;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2018/10/13.
 *
 * @author zhangyizhi
 */
public class ProviderConnectedView extends AbstractView {
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (model.get("connections") == null) {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().write("解绑成功");
		} else {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write("<h3>绑定成功</h3>");
		}
	}
}
