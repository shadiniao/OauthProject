package com.zyz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
public interface ValidateCodeProcessor {
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	// 创建验证码
	void create(ServletWebRequest request) throws Exception; // ServletWebRequest是一个对request,response进行包装的类, 这样在同时需要request和response时不用传两个参数
}
