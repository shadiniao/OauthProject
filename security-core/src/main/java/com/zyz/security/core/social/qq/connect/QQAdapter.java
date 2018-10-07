package com.zyz.security.core.social.qq.connect;

import com.zyz.security.core.social.qq.api.QQ;
import com.zyz.security.core.social.qq.api.QQUserInfo;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import java.io.IOException;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class QQAdapter implements ApiAdapter<QQ> {
	// 判断服务供应商连接是否畅通
	@Override
	public boolean test(QQ qq) {
		return true;
	}

	// 进行用户数据的适配工作
	@Override
	public void setConnectionValues(QQ api, ConnectionValues connectionValues) {
		QQUserInfo qqUserInfo = api.getUserInfo();

		connectionValues.setDisplayName(qqUserInfo.getNickname());
		connectionValues.setImageUrl(qqUserInfo.getFigureurl_qq_1());
		connectionValues.setProfileUrl(null);
		connectionValues.setProviderUserId(qqUserInfo.getOpenId());
	}

	@Override
	public UserProfile fetchUserProfile(QQ api) {
		return null;
	}

	@Override
	public void updateStatus(QQ api, String s) {
		// 更新用户信息, qq没有该功能, 微博有
	}
}
