package com.zyz.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zyz.validator.CheckType;
import com.zyz.validator.UserConstraint;

import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

import javax.validation.constraints.Past;

/**
 * Created by yizhi on 2018-08-26.
 */
public class User {
    private String id;
    @UserConstraint(message = "用户名重复", groups = CheckType.CreateCheck.class)
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @Past(message = "生日必须为过去的时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public interface UserSimpleView {

    }

    public interface UserDetailView extends UserSimpleView {

    }


}
