package com.wyhcode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiyuhui
 * @date 2023/7/31 16:45
 * @description
 */

@Slf4j
@SpringBootApplication
public class OssDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(OssDemoApplication.class,args);
        log.info("项目启动完成");
    }
}

