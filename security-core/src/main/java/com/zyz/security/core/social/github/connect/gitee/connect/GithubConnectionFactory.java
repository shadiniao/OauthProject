package com.zyz.security.core.social.github.connect.gitee.connect;

import com.zyz.security.core.social.github.connect.gitee.api.GithubApi;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GithubConnectionFactory extends OAuth2ConnectionFactory<GithubApi> {
	public GithubConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new GithubServiceProvider(appId, appSecret), new GithubAdapter());
	}
}
