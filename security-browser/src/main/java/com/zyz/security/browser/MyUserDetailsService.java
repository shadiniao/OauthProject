package com.zyz.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 2018/9/4.
 *
 * @author zhangyizhi
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("登陆用户名:" + username);
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码是:" + password);
        if ("user1".equals(username)) {
            return new User(username, password, true, true, true, false, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        } else if ("user2".equals(username)) {
            return new User(username, "$2a$10$Th5q4wk11HLMpn7DZiCpLe.cJ8LiaipEvt5NQqPZLAnWaJeIu3ZNq", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        }
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
