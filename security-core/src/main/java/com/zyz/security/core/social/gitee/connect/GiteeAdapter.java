package com.zyz.security.core.social.gitee.connect;

import com.zyz.security.core.social.gitee.api.GiteeApi;
import com.zyz.security.core.social.gitee.api.GiteeUserInfo;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GiteeAdapter implements ApiAdapter<GiteeApi> {
	// 判断服务供应商连接是否畅通
	@Override
	public boolean test(GiteeApi api) {
		return true;
	}

	// 进行用户数据的适配工作
	@Override
	public void setConnectionValues(GiteeApi api, ConnectionValues connectionValues) {
		GiteeUserInfo userInfo = api.getUserInfo();

		connectionValues.setDisplayName(userInfo.getName());
		connectionValues.setImageUrl(userInfo.getAvatar_url());
		connectionValues.setProfileUrl(null);
		connectionValues.setProviderUserId(String.valueOf(userInfo.getId()));
	}

	@Override
	public UserProfile fetchUserProfile(GiteeApi api) {
		return null;
	}

	@Override
	public void updateStatus(GiteeApi api, String s) {
		// 更新用户信息, qq没有该功能, 微博有
	}
}
