package com.wyhcode.service.user;

import com.wyhcode.entity.User;
import com.wyhcode.mapper.UserMapper;
import com.wyhcode.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @date 2023/6/16 15:06
 * @description
 */

@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private JwtUtil jwtUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public String login(String userName, String password) {

        return null;
    }
}
