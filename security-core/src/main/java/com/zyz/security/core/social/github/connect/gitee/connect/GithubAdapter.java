package com.zyz.security.core.social.github.connect.gitee.connect;

import com.zyz.security.core.social.github.connect.gitee.api.GithubApi;
import com.zyz.security.core.social.github.connect.gitee.api.GithubUserInfo;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class GithubAdapter implements ApiAdapter<GithubApi> {
	// 判断服务供应商连接是否畅通
	@Override
	public boolean test(GithubApi api) {
		return true;
	}

	// 进行用户数据的适配工作
	@Override
	public void setConnectionValues(GithubApi api, ConnectionValues connectionValues) {
		GithubUserInfo userInfo = api.getUserInfo();

		connectionValues.setDisplayName(userInfo.getName());
		connectionValues.setImageUrl(userInfo.getAvatar_url());
		connectionValues.setProfileUrl(null);
		connectionValues.setProviderUserId(String.valueOf(userInfo.getId()));
	}

	@Override
	public UserProfile fetchUserProfile(GithubApi api) {
		return null;
	}

	@Override
	public void updateStatus(GithubApi api, String s) {
		// 更新用户信息, qq没有该功能, 微博有
	}
}
