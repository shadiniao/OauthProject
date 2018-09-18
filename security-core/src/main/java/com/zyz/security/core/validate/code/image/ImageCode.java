package com.zyz.security.core.validate.code.image;

import com.zyz.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
public class ImageCode extends ValidateCode {
	private BufferedImage image;

	public ImageCode(BufferedImage image, String code, int expireIn) {
		super(code, expireIn);
		this.image = image;
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
