package com.wyhcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiyuhui
 * @date 2023/8/17 17:38
 * @description
 */


@SpringBootApplication
@Slf4j
public class UseApplication {
    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        SpringApplication.run(UseApplication.class, args);
        log.info("项目启动耗时： {} s",started.stop().elapsed().getSeconds());
    }
}
