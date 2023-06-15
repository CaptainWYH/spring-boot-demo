package com.wyhcode.controller;

import com.wyhcode.common.ApiResponse;
import com.wyhcode.entity.vo.JwtResponse;
import com.wyhcode.entity.vo.LoginRequest;
import com.wyhcode.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
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
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ApiResponse login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = jwtUtil.createJWT(authenticate, loginRequest.getRememberMe());
        return ApiResponse.ofSuccess(new JwtResponse(jwt));
    }

    @GetMapping("/login.html")
    public String loginPage(){
        return "login";
    }
}
