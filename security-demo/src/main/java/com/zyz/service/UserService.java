package com.zyz.service;

import com.zyz.dto.UserQueryCondition;

/**
 * 2018/8/29.
 *
 * @author zhangyizhi
 */
public interface UserService {
    int count(UserQueryCondition userQueryCondition);
}
