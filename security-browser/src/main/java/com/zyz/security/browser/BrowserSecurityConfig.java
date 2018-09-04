package com.zyz.security.browser;

import com.zyz.security.core.properties.SecurityProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 2018/9/3.
 *
 * @author zhangyizhi
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private SecurityProperties securityProperties;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单登录
                .loginPage("/authentication/require") // 登录页面
                .loginProcessingUrl("/authentication/form") // 登录认证的请求地址
                .and()
                .authorizeRequests() // 对请求授权, 下面的语句都会受影响
                .antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll() // 访问login.html不需要身份验证, 如果不配置这一行, 会造成死循环
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                .and()
                .csrf().disable(); // 暂时屏蔽csrf
    }
}
