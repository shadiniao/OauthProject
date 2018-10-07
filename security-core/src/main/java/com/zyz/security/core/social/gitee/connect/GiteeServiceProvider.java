package com.zyz.security.core.social.gitee.connect;

import com.zyz.security.core.social.gitee.api.GiteeApi;
import com.zyz.security.core.social.gitee.api.GiteeApiImpl;
import com.zyz.security.core.social.qq.api.QQ;
import com.zyz.security.core.social.qq.api.QQImpl;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GiteeServiceProvider extends AbstractOAuth2ServiceProvider<GiteeApi> {
	public static final String URL_ACCESS_TOKEN = "https://gitee.com/oauth/token";
	public static final String URL_AUTHORIZE = "https://gitee.com/oauth/authorize";

	public GiteeServiceProvider(String appId, String appSecret) {
		super(new OAuth2Template(appId, appSecret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
	}

	@Override
	public GiteeApi getApi(String accessToken) {
		return new GiteeApiImpl(accessToken);
	}
}
