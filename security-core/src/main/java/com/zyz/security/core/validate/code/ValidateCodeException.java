package com.zyz.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
