package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityConstants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
	private ValidateCodeProcessorHolder validateCodeProcessorHolder;

	@GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
	public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
			throws Exception {
		ValidateCodeProcessor validateCodeProcessor = validateCodeProcessorHolder.findValidateCodeProcessor(type);

		if (validateCodeProcessor != null) {
			validateCodeProcessor.create(new ServletWebRequest(request, response));
		}
	}
}
