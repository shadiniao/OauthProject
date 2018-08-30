package com.zyz.validator;

import com.zyz.dto.UserQueryCondition;
import com.zyz.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 2018/8/29.
 *
 * @author zhangyizhi
 */
public class UserRepeatConstraintValidator implements ConstraintValidator<UserConstraint, String> {
    @Autowired
    private UserService userService;

    private String field;

    @Override
    public void initialize(UserConstraint constraintAnnotation) {
        this.field = constraintAnnotation.field();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        UserQueryCondition condition = new UserQueryCondition();

        if (field.equals("username")) {
            condition.setUsername(value);
        }

        return userService.count(condition) == 0;
    }
}
