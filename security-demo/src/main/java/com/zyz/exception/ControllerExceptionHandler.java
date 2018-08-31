package com.zyz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * 2018/8/30.
 *
 * @author zhangyizhi
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleBusinessException(BusinessException ex) {
        System.out.println("进入handleBusinessException");

        Map<String, Object> result = new HashMap<>();
        result.put("message", ex.getMessage());

        return result;
    }
}
