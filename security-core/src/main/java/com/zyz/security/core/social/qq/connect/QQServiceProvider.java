package com.zyz.security.core.social.qq.connect;

import com.zyz.security.core.social.qq.api.QQ;
import com.zyz.security.core.social.qq.api.QQImpl;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
	public static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";
	public static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
	private String appId;

	public QQServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public QQ getApi(String accessToken) {
		return new QQImpl(accessToken, appId);
	}
}
