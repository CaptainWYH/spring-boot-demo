package com.wyhcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author weiyuhui
 * @date 2023/8/9 13:57
 * @description
 */

@RequestMapping("/minio")
@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
