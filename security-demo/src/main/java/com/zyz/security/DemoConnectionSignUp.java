package com.zyz.security;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 2018/9/23.
 *
 * @author zhangyizhi
 */
@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public String execute(Connection<?> connection) {
		String pid = RandomStringUtils.randomNumeric(5);
		logger.info(connection.getKey().getProviderId() + " social登录自动注册" + connection.getDisplayName());
		logger.info("用户:" + connection.getDisplayName() + ", 生成业务pid:" + pid + ", 并绑定");
		return pid;
	}
}
