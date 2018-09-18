package com.zyz.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by yizhi on 2018-09-16.
 */
public interface ValidateCodeGenerator {

    ValidateCode createCode(ServletWebRequest request);
}
