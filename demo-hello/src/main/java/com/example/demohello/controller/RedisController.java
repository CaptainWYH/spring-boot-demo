package com.example.demohello.controller;

import com.wyhcode.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @Date: 2025/6/10 22:57
 **/
@RestController
@RequestMapping("/redis")
public class RedisController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/set/{key}/{value}")
    public String set(@PathVariable("key") String key, @PathVariable("value") Object value) {
        redisTemplate.opsForValue().set(key,value);
        return "success";
    }
}
