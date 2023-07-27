package com.wyhcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author weiyuhui
 * @date 2023/7/25 14:29
 * @description
 */

@SpringBootApplication
@ComponentScan("com.wyhcode.*")
@Slf4j
public class ElasticSearchApplication {
    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        SpringApplication.run(ElasticSearchApplication.class, args);
        started.stop();
        log.info("项目启动耗时{}s",started.elapsed().getSeconds());
    }
}
