package com.wyhcode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author weiyuhui
 * @date 2023/6/15 18:07
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoSecurityApplicationTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void test1(){
        System.out.println(redisTemplate);
        redisTemplate.opsForValue().set("test","test");
    }
}
