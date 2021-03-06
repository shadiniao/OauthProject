package com.zyz.security.core.social.gitee.config;

import com.zyz.security.core.properties.GiteeProperties;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.social.ProviderConnectedView;
import com.zyz.security.core.social.gitee.connect.GiteeConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
@Configuration
@Order(501)
// 只有当配置了gitee相关的property该类才会生效
@ConditionalOnProperty(prefix = "core.security.social.gitee", name = "app-id")
public class GiteeAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		GiteeProperties properties = securityProperties.getSocial().getGitee();
		return new GiteeConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
	}

	@Bean({"connect/giteeConnected", "connect/giteeConnect"})
	@ConditionalOnMissingBean(name = "connect/giteeConnected")
	public View giteeConnectedView() {
		return new ProviderConnectedView();
	}
}
