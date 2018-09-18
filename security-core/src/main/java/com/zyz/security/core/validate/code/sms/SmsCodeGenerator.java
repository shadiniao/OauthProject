package com.zyz.security.core.validate.code.sms;

import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.ValidateCode;
import com.zyz.security.core.validate.code.ValidateCodeGenerator;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * Created by yizhi on 2018-09-16.
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

	@Autowired
	private SecurityProperties securityProperties;

	@Override
	public ValidateCode createCode(ServletWebRequest request) {
		String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
		return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
	}
}
