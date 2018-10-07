package com.zyz.security.core.social.qq.config;

import com.zyz.security.core.properties.QQProperties;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.social.qq.connect.QQConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
@Configuration
// 只有当配置了qq相关的property该类才会生效
@ConditionalOnProperty(prefix = "core.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		QQProperties properties = securityProperties.getSocial().getQq();
		return new QQConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
	}
}
