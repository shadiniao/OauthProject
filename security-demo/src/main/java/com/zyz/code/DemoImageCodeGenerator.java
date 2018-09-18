package com.zyz.code;

import com.zyz.security.core.validate.code.image.ImageCode;
import com.zyz.security.core.validate.code.ValidateCodeGenerator;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by yizhi on 2018-09-16.
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ImageCode createCode(ServletWebRequest request) {
        System.out.println("demo image code");
        return null;
    }
}
