package com.wyhcode.service.user;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyhcode.entity.User;
import com.wyhcode.entity.vo.user.LoginUserResponseVO;
import com.wyhcode.mapper.UserMapper;
import com.wyhcode.util.JwtManager;
import com.wyhcode.util.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.Collections;

import static com.wyhcode.enums.StatusEnum.USERNAME_PASSWORD_ERROR;
import static com.wyhcode.util.ServiceExceptionUtil.exception;

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

    @Resource
    private JwtManager jwtManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(User user) {
        user.setCreateTime(System.currentTimeMillis());
        userMapper.insert(user);
    }

    @Override
    public LoginUserResponseVO login(String userName, String password) {
        //从数据库查出用户
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, userName));
        // 若没有查询到  或者密码校验错误   抛出异常信息
        if (ObjectUtil.isNull(user) || !passwordEncoder.matches(password,user.getPassword())){
            throw exception(USERNAME_PASSWORD_ERROR);
        }
        LoginUserResponseVO userResponseVO = new LoginUserResponseVO();
        userResponseVO.setId(user.getId());
        userResponseVO.setUserName(user.getUsername());
        userResponseVO.setToken(jwtUtil.createJWT(true,user.getId(),user.getUsername(),Collections.emptyList(),Collections.emptyList()));
        return userResponseVO;
    }
}
