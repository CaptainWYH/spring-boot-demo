package com.wyhcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiyuhui
 * @date 2023/8/8 11:24
 * @description
 */

@Slf4j
@SpringBootApplication
public class MinioApplication {
    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        SpringApplication.run(MinioApplication.class,args);
        log.info("项目启动耗时： {} s",started.stop().elapsed().getSeconds());
    }
}
