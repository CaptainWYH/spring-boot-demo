package com.wyhcode.controller;

import com.wyhcode.common.ApiResponse;
import com.wyhcode.entity.vo.user.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiyuhui
 * @date 2023/6/16 15:58
 * @description
 */

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test")
    public ApiResponse test(){
        return ApiResponse.ofSuccess("访问test接口成功");
    }

    @GetMapping("/monitor")
    public ApiResponse monitor(){
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("当前登陆用户{}",principal);
        return ApiResponse.ofSuccess(principal);
    }
}
