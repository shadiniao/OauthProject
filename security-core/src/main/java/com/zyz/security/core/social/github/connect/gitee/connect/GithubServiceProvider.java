package com.zyz.security.core.social.github.connect.gitee.connect;

import com.zyz.security.core.social.github.connect.gitee.api.GithubApi;
import com.zyz.security.core.social.github.connect.gitee.api.GithubApiImpl;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GithubServiceProvider extends AbstractOAuth2ServiceProvider<GithubApi> {
	public static final String URL_ACCESS_TOKEN = "https://github.com/login/oauth/access_token";
	public static final String URL_AUTHORIZE = "https://github.com/login/oauth/authorize";

	public GithubServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public GithubApi getApi(String accessToken) {
		return new GithubApiImpl(accessToken);
	}
}
