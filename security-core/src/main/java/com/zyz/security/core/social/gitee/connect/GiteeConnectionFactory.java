package com.zyz.security.core.social.gitee.connect;

import com.zyz.security.core.social.gitee.api.GiteeApi;
import com.zyz.security.core.social.qq.api.QQ;
import com.zyz.security.core.social.qq.connect.QQAdapter;
import com.zyz.security.core.social.qq.connect.QQServiceProvider;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GiteeConnectionFactory extends OAuth2ConnectionFactory<GiteeApi> {
	public GiteeConnectionFactory(String providerId, String appId, String appSecret) {
		super(providerId, new GiteeServiceProvider(appId, appSecret), new GiteeAdapter());
	}
}
