package com.zyz.security.core.validate.code.sms;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
	@Override
	public void send(String mobile, String content) {
		System.out.println("mobile [" + mobile + "]:" + content);
	}
}
