package com.zyz.security.core.social.gitee.config;

import com.zyz.security.core.properties.GiteeProperties;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.social.gitee.connect.GiteeConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;

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

//	@Autowired(required = false)
//	private ConnectionSignUp connectionSignUp;
//
//	@Override
//	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//		// Encryptors对数据加解密用的, 这里不进行加解密
//		JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//		jdbcUsersConnectionRepository.setTablePrefix("t_");
//		jdbcUsersConnectionRepository.setConnectionSignUp(connectionSignUp);
//		return jdbcUsersConnectionRepository;
//	}

	@Override
	protected ConnectionFactory<?> createConnectionFactory() {
		GiteeProperties properties = securityProperties.getSocial().getGitee();
		return new GiteeConnectionFactory(properties.getProviderId(), properties.getAppId(), properties.getAppSecret());
	}
}
