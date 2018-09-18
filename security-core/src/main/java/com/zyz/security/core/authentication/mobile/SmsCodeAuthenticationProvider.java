package com.zyz.security.core.authentication.mobile;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 2018/9/18.
 *
 * @author zhangyizhi
 */
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {
	private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		SmsCodeAuthenticationToken smsCodeAuthenticationToken = (SmsCodeAuthenticationToken) authentication;
		UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsCodeAuthenticationToken.getPrincipal());

		SmsCodeAuthenticationToken result = new SmsCodeAuthenticationToken(userDetails, userDetails.getAuthorities());
		result.setDetails(smsCodeAuthenticationToken.getDetails());

		return result;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
}
