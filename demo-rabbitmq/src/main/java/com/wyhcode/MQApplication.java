package com.wyhcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author weiyuhui
 * @date 2023/7/14 10:58
 * @description
 */

@Slf4j
@SpringBootApplication
public class MQApplication {
    public static void main(String[] args) {
        Stopwatch time = Stopwatch.createStarted();
        SpringApplication.run(MQApplication.class,args);
        time.stop();
        log.info("项目启动耗时：{} s",time.elapsed().getSeconds());
    }
}
