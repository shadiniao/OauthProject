package com.zyz.web.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * 2018/8/30.
 *
 * @author zhangyizhi
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("time filter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("time filter start");
        long start = new Date().getTime();
        filterChain.doFilter(servletRequest, servletResponse);
        long end = new Date().getTime();
        System.out.println("time filter 耗时:" + (end - start));
        System.out.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.out.println("time filter destroy");
    }
}
