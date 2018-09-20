package com.zyz.security.core.validate.code;

import com.zyz.security.core.properties.SecurityConstants;

/**
 * 2018/9/19.
 *
 * @author zhangyizhi
 */
public enum ValidateCodeType {
	SMS {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_SMS;
		}
	}, IMAGE {
		@Override
		public String getParamNameOnValidate() {
			return SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMAGE;
		}
	};

	public abstract String getParamNameOnValidate();
}
