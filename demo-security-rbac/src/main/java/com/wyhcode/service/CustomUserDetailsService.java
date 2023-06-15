package com.wyhcode.service;

/**
 * @author weiyuhui
 * @date 2023/6/13 17:35
 * @description
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wyhcode.entity.Permission;
import com.wyhcode.entity.Role;
import com.wyhcode.entity.User;
import com.wyhcode.entity.vo.UserPrincipal;
import com.wyhcode.mapper.PermissionMapper;
import com.wyhcode.mapper.RoleMapper;
import com.wyhcode.mapper.UserMapper;
import com.wyhcode.mapper.UserRoleMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义查询用户
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //根据用户名获取用户信息
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>()
                .eq(User::getUsername, username)
                .or().eq(User::getEmail, username)
                .or().eq(User::getPhone, username));
        //获取用户相关角色
        List<Role> roles = roleMapper.selectByUserId(user.getId());
        //提取角色id
        List<Long> roleIds = roles.stream().map(Role::getId).collect(Collectors.toList());
        //获取相关权限信息
        List<Permission> permissionList = permissionMapper.selectByRoleId(roleIds);
        return UserPrincipal.create(user,roles,permissionList);
    }
}
