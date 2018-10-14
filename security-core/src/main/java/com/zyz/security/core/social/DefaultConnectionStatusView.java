package com.zyz.security.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 显示哪些第三方社交进行了绑定
@Component("connect/status")
public class DefaultConnectionStatusView extends AbstractView {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String, List<Connection<?>>> connectionMap = (Map<String, List<Connection<?>>>) model.get("connectionMap");

		Set<String> keys = connectionMap.keySet();

		Map<String, Boolean> result = new HashMap<>();

		for (String key : keys) {
			List<Connection<?>> connections = connectionMap.get(key);
			if (connections.size() == 0) {
				result.put(key, false);
			} else {
				result.put(key, true);
			}
		}

		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(result));
	}
}
