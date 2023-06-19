package com.wyhcode.controller;

import com.wyhcode.common.ApiResponse;
import com.wyhcode.entity.User;
import com.wyhcode.entity.vo.JwtResponse;
import com.wyhcode.entity.vo.LoginRequest;
import com.wyhcode.entity.vo.user.UserBaseVO;
import com.wyhcode.enums.StatusEnum;
import com.wyhcode.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author weiyuhui
 * @date 2023/6/14 17:12
 * @description
 */

@RestController
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest){
        //AuthenticationManager校验这个认证信息，返回一个已认定的Authentication    UsernamePasswordAuthenticationToken 封装用户信息
//        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));
        //将返回的认证信息存储到上下文中
//        SecurityContextHolder.getContext().setAuthentication(authenticate);

        //使用自己的登陆业务  TODO 登陆业务待做
        String token = userService.login(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword());
        return ApiResponse.ofSuccess(token);
    }

    @PostMapping("/register")
    public ApiResponse register(@RequestBody UserBaseVO userBaseVO){
        User user = new User();
        BeanUtils.copyProperties(userBaseVO,user);
        userService.save(user);
        return ApiResponse.ofSuccess("注册成功");
    }

    @GetMapping("/logout")
    public ApiResponse logout(){
        SecurityContextHolder.clearContext();
        return ApiResponse.ofSuccess(StatusEnum.LOGOUT);
    }
}
