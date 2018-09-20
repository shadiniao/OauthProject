package com.zyz.security.core.validate.code.impl;

import com.zyz.security.core.properties.SecurityConstants;
import com.zyz.security.core.validate.code.ValidateCode;
import com.zyz.security.core.validate.code.ValidateCodeException;
import com.zyz.security.core.validate.code.ValidateCodeGenerator;
import com.zyz.security.core.validate.code.ValidateCodeProcessor;
import com.zyz.security.core.validate.code.ValidateCodeType;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
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

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

	@Override
	public void validate(ServletWebRequest request) throws ValidateCodeException {
		ValidateCodeType validateCodeType = getValidateCodeType();
		String sessionName = ValidateCodeProcessor.SESSION_KEY_PREFIX + validateCodeType.toString().toUpperCase();
		C validateCode = (C) sessionStrategy.getAttribute(request, sessionName);
		String codeInRequest;
		try {
			codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), validateCodeType.getParamNameOnValidate());
		} catch (ServletRequestBindingException e) {
			throw new ValidateCodeException("获取验证码的值失败");
		}

		if (StringUtils.isBlank(codeInRequest)) {
			throw new ValidateCodeException("验证码的值不能为空");
		}

		if (validateCode == null) {
			throw new ValidateCodeException("验证码不存在");
		}

		if (validateCode.isExpired()) {
			sessionStrategy.removeAttribute(request, sessionName);
			throw new ValidateCodeException("验证码已过期");
		}

		if (!StringUtils.equals(codeInRequest, validateCode.getCode())) {
			throw new ValidateCodeException("验证码不匹配");
		}

		sessionStrategy.removeAttribute(request, sessionName);
	}

	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = this.generator(request);
		this.save(request, validateCode);
		this.send(request, validateCode);

	}

	private C generator(ServletWebRequest request) {
		String type = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
		return (C) validateCodeGenerator.createCode(request);
	}

	private ValidateCodeType getValidateCodeType() {
		String type = StringUtils.substringBefore(getClass().getSimpleName(), "CodeProcessor").toUpperCase();
		return ValidateCodeType.valueOf(type);
	}

	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/");
	}

	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(), validateCode);
	}
}
