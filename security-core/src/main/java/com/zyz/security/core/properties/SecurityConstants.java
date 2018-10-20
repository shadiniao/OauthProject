package com.zyz.security.core.properties;

/**
 * 2018/9/19.
 *
 * @author zhangyizhi
 */
public interface SecurityConstants {

    String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";

    String DEFAULT_UNAUTHENTICATION_URL = "/authentication/require";

    String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";

    String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";

    String DEFAULT_LOGIN_PAGE_URL = "/login.html";

    String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";

    String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";

    String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";

    String DEFAULT_INVALID_SESSION_URL = "/invalid-session.html";
}
