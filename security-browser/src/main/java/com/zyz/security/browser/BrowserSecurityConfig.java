package com.zyz.security.browser;

import com.zyz.security.browser.authentication.CoreAuthenticationFailureHandler;
import com.zyz.security.browser.authentication.CoreAuthenticationSuccessHandler;
import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.ValidateCodeController;
import com.zyz.security.core.validate.code.ValidateCodeFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 2018/9/3.
 *
 * @author zhangyizhi
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private CoreAuthenticationFailureHandler coreAuthenticationFailureHandler;

    @Autowired
    private CoreAuthenticationSuccessHandler coreAuthenticationSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(coreAuthenticationFailureHandler);
        validateCodeFilter.setSecurityProperties(securityProperties);
        validateCodeFilter.afterPropertiesSet();

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin() // 表单登录
                .loginPage("/authentication/require") // 登录页面
                .loginProcessingUrl("/authentication/form") // 登录认证的请求地址
                .successHandler(coreAuthenticationSuccessHandler)
                .failureHandler(coreAuthenticationFailureHandler)
                .and()
                .authorizeRequests() // 对请求授权, 下面的语句都会受影响
                .antMatchers("/authentication/require",
                        securityProperties.getBrowser().getLoginPage(),
                        "/code/image").permitAll() // 访问login.html不需要身份验证, 如果不配置这一行, 会造成死循环
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                .and()
                .csrf().disable(); // 暂时屏蔽csrf
    }
}
