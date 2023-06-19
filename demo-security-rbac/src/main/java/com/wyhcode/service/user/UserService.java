package com.wyhcode.service.user;

import com.wyhcode.entity.User;

/**
 * @author weiyuhui
 * @date 2023/6/16 15:04
 * @description
 */

public interface UserService {
    void save(User user);

    /**
     * 登陆认证
     * @param userName
     * @param password
     * @return
     */
    String login(String userName,String password);
}
