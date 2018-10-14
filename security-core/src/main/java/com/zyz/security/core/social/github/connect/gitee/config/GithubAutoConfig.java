package com.zyz.security.core.social.github.connect.gitee.config;

import com.zyz.security.core.properties.GithubProperties;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.social.ProviderConnectedView;
import com.zyz.security.core.social.github.connect.gitee.connect.GithubConnectionFactory;

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
// 只有当配置了github相关的property该类才会生效
@ConditionalOnProperty(prefix = "core.security.social.github", name = "app-id")
public class GithubAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		GithubProperties properties = securityProperties.getSocial().getGithub();
		return new GithubConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
	}

	@Bean({"connect/githubConnect", "connect/githubConnected"})
	@ConditionalOnMissingBean(name = "connect/githubConnected")
	public View githubConnectedView() {
		return new ProviderConnectedView();
	}
}
