package com.zyz.security.core.social.gitee.api;

import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

/**
 * 2018/9/21.
 *
 * @author zhangyizhi
 */
public class GiteeApiImpl extends AbstractOAuth2ApiBinding implements GiteeApi {

	private static final String URL_GET_USER_INFO = "https://gitee.com/api/v5/user?access_token=%s";

	private final String accessToken;

	public GiteeApiImpl(String accessToken) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.accessToken = accessToken;
	}

	@Override
	public GiteeUserInfo getUserInfo() {
		String url = String.format(URL_GET_USER_INFO, this.accessToken);
		return getRestTemplate().getForObject(url, GiteeUserInfo.class);
	}
}
