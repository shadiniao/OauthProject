package com.zyz.exception;

/**
 * 2018/8/30.
 *
 * @author zhangyizhi
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
