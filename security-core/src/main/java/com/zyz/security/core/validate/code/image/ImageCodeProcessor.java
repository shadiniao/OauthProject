package com.zyz.security.core.validate.code.image;

import com.zyz.security.core.validate.code.impl.AbstractValidateCodeProcessor;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
	@Override
	protected void send(ServletWebRequest request, ImageCode validateCode) throws IOException {
		ImageIO.write(validateCode.getImage(), "JPEG", request.getResponse().getOutputStream());
	}
}
