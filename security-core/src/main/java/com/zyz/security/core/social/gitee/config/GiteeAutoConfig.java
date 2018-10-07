package com.zyz.security.core.social.gitee.config;

import com.zyz.security.core.properties.GiteeProperties;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.social.gitee.connect.GiteeConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

import javax.sql.DataSource;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
@Configuration
// 只有当配置了gitee相关的property该类才会生效
@ConditionalOnProperty(prefix = "core.security.social.gitee", name = "app-id")
public class GiteeAutoConfig extends SocialAutoConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private DataSource dataSource;

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		GiteeProperties properties = securityProperties.getSocial().getGitee();
		return new GiteeConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
	}
}
