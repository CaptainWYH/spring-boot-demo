package com.wyhcode.service;

import com.wyhcode.entity.User;

/**
 * @author weiyuhui
 * @date 2023/6/13 14:39
 * @description
 */

public interface UserService {

    /**
     * 保存\更新用户
     * @param user
     * @return
     */
    public User saveOrUpdate(User user);

    /**
     * 删除用户
     * @param id
     */
    void delete(Long id);

    /**
     * 获取用户
     * @param id
     * @return
     */
    User get(Long id);
}
