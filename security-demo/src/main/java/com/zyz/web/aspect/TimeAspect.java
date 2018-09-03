package com.zyz.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

//@Aspect
//@Component
public class TimeAspect {
    // 第一个*表示任何返回值, 第二个*表示任何方法, (..)这个方法包含任何参数
    // 也就是表示UserController中的任何方法
    @Around("execution(* com.zyz.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        long start = new Date().getTime();

        Object[] args = pjp.getArgs();

        for (Object arg : args) {
            System.out.println("arg is:" + arg);
        }

        Object result = pjp.proceed();
        long end = new Date().getTime();
        System.out.println("time aspect 耗时:" + (end - start));

        return result;
    }
}
