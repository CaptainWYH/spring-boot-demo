package com.wyhcode;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MybatisApplication {
    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        SpringApplication.run(MybatisApplication.class,args);
        started.stop();
        log.info("项目启动耗时{}s",started.elapsed().getSeconds());
    }
}
