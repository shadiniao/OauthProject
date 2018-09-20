package com.zyz.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * 2018/9/20.
 *
 * @author zhangyizhi
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
	public static final String URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

	public static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

	private String openId;

	private String appId;

	private ObjectMapper objectMapper = new ObjectMapper();

	public QQImpl(String accessToken, String appId) {
		super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
		this.appId = appId;

		String url = String.format(URL_GET_OPENID, accessToken);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);

		this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
	}

	@Override
	public QQUserInfo getUserInfo() throws IOException {
		String url = String.format(URL_GET_USER_INFO, this.appId, this.openId);
		String result = getRestTemplate().getForObject(url, String.class);
		System.out.println(result);
		return objectMapper.readValue(result, QQUserInfo.class);
	}
}
