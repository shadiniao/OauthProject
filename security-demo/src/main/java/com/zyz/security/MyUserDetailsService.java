package com.zyz.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
@Component
public class MyUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录,用户名:" + username);
		return buildUser(username);
	}

	// 参数userId是业务中的用户id, 不是服务供应商的openId
	@Override
	public SocialUser loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("社交登录,用户名:" + userId);
		return buildUser(userId);
	}

	private SocialUser buildUser(String userId) {
		String password = passwordEncoder.encode("123456");
		logger.info("数据库密码是:" + password);
		if ("user1".equals(userId)) {
			return new SocialUser(userId, password, true, true, true, false, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		} else if ("user2".equals(userId)) {
			return new SocialUser(userId, "$2a$10$Th5q4wk11HLMpn7DZiCpLe.cJ8LiaipEvt5NQqPZLAnWaJeIu3ZNq", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		}
		return new SocialUser(userId, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
}
