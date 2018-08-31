package com.zyz.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class TimeInterceptor implements HandlerInterceptor {
    // 调用之前进入该方法
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String className = handlerMethod.getBean().getClass().getName();
        String methodName = handlerMethod.getMethod().getName();

        System.out.println("preHandle:" + className + ".." + methodName);
        // 只能通过setAttribute的方式传递变量
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true; // 如果返回false就不会往下执行了
    }

    // 调用完成后进入该方法,但抛出异常的话不会进入该方法
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        long endTime = new Date().getTime();

        System.out.println("postHandle 耗时:" + (endTime - startTime));
    }

    // 不管是否有异常抛出,最后都会进入该方法
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        long endTime = new Date().getTime();
        System.out.println("afterCompletion 耗时:" + (endTime - startTime) + ",exception is:" + e);
    }
}
