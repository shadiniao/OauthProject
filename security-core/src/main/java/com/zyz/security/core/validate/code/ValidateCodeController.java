package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityProperties;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStraegy = new HttpSessionSessionStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeGenerator validateCodeGenerator;

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ImageCode imageCode = validateCodeGenerator.createImageCode(new ServletWebRequest(request));
        sessionStraegy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }
}
