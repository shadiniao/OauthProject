package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityProperties;
import com.zyz.security.core.validate.code.image.ImageCode;
import com.zyz.security.core.validate.code.sms.SmsCodeSender;

import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
@RestController
public class ValidateCodeController {
	@Autowired
	private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

	@GetMapping("/code/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorMap.get(type + "CodeProcessor");
		if (validateCodeProcessor != null) {
			validateCodeProcessor.create(new ServletWebRequest(request, response));
		}
	}
}
