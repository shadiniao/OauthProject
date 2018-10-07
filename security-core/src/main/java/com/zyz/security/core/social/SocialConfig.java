package com.zyz.security.core.social;

import com.zyz.security.core.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private SecurityProperties securityProperties;

	// connectionFactoryLocator参数用于查找之前编写好的ConnectionFactory, 因为系统中可能有多种
	@Override
	public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
		// Encryptors对数据加解密用的, 这里不进行加解密
		JdbcUsersConnectionRepository jdbcUsersConnectionRepository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
		jdbcUsersConnectionRepository.setTablePrefix("t_");
		return jdbcUsersConnectionRepository;
	}

	@Bean
	public SpringSocialConfigurer coreSpringSocialConfigurer() {
		CoreSpringSocialConfigurer coreSpringSocialConfigurer = new CoreSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
//		coreSpringSocialConfigurer.signupUrl(securityProperties.getBrowser().getSignUpUrl());
		return coreSpringSocialConfigurer;

//		return new SpringSocialConfigurer();
	}

	@Bean
	public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator) {
		return new ProviderSignInUtils(connectionFactoryLocator, getUsersConnectionRepository(connectionFactoryLocator));
	}
}
