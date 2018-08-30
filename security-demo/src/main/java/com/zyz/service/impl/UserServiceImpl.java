package com.zyz.service.impl;

import com.zyz.dto.UserQueryCondition;
import com.zyz.service.UserService;

import org.springframework.stereotype.Service;

/**
 * 2018/8/29.
 *
 * @author zhangyizhi
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public int count(UserQueryCondition userQueryCondition) {
        if (userQueryCondition == null) return 0;

        String username = userQueryCondition.getUsername();
        if (username.equals("admin")) return 1;

        return 0;
    }
}
