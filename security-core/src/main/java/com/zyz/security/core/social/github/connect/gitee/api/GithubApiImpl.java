package com.zyz.security.core.social.github.connect.gitee.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * 2018/9/21.
 *
 * @author zhangyizhi
 */
public class GithubApiImpl extends AbstractOAuth2ApiBinding implements GithubApi {

	private static final String URL_GET_USER_INFO = "https://api.github.com/user?access_token=%s";

	private final String accessToken;

	public GithubApiImpl(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.accessToken = accessToken;
	}

	@Override
	public GithubUserInfo getUserInfo() {
		String url = String.format(URL_GET_USER_INFO, this.accessToken);
		return getRestTemplate().getForObject(url, GithubUserInfo.class);
	}
}
