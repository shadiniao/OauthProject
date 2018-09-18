package com.zyz.security.core.validate.code.sms;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
public interface SmsCodeSender {
	void send(String mobile, String content);
}
