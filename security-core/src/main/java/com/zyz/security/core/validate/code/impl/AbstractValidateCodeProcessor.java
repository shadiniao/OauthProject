package com.zyz.security.core.validate.code.impl;

import com.zyz.security.core.validate.code.ValidateCode;
import com.zyz.security.core.validate.code.ValidateCodeGenerator;
import com.zyz.security.core.validate.code.ValidateCodeProcessor;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements
        ValidateCodeProcessor {

    // spring会在启动后收集所有的ValidateCodeGenerator类型, 放入到这个map中, map中的key就是类型名称
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    private SessionStrategy sessionStraegy = new HttpSessionSessionStrategy();

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = this.generator(request);
        this.save(request, validateCode);
        this.send(request, validateCode);

    }

    private C generator(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap
                .get(type + "CodeGenerator");
        return (C) validateCodeGenerator.createCode(request);
    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
    }

    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    protected void save(ServletWebRequest request, C validateCode) {
        sessionStraegy
                .setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),
                        validateCode);
    }
}
