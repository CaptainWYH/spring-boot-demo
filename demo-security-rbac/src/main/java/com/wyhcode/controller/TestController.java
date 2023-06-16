package com.wyhcode.controller;

import com.wyhcode.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weiyuhui
 * @date 2023/6/16 15:58
 * @description
 */

@RestController
public class TestController {

    @GetMapping("/test")
    public ApiResponse test(){
        return ApiResponse.ofSuccess("访问test接口成功");
    }

    @GetMapping("/monitor")
    public ApiResponse monitor(){
        return ApiResponse.ofSuccess("监控页面");
    }
}
