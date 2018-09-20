package com.zyz.security.core.validate.code;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 2018/9/19.
 *
 * @author zhangyizhi
 */
@Component
public class ValidateCodeProcessorHolder {
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

	public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
		return findValidateCodeProcessor(type.toString().toLowerCase());
	}

	public ValidateCodeProcessor findValidateCodeProcessor(String type) {
		String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(name);
		if (validateCodeProcessor == null) {
			throw new ValidateCodeException("ValidateCodeProcessor is not exist: " + name);
		}
		return validateCodeProcessor;
	}
}
