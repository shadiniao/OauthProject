package com.zyz.web.config;

import com.zyz.web.filter.TimeFilter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 2018/8/30.
 *
 * @author zhangyizhi
 */
@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean timeFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();

        TimeFilter timeFilter = new TimeFilter();

        filterRegistrationBean.setFilter(timeFilter);

        List<String> urls = new ArrayList<>();
        urls.add("/user/*");
        filterRegistrationBean.setUrlPatterns(urls);

        return filterRegistrationBean;
    }
}
