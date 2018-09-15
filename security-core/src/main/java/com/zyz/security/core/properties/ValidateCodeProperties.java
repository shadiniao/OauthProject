package com.zyz.security.core.properties;

/**
 * 2018/9/15.
 *
 * @author zhangyizhi
 */
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }
}
