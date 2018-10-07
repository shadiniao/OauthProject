package com.zyz.security.core.social.qq.connect;

import com.zyz.security.core.social.qq.api.QQ;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
	public QQConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
	}
}
