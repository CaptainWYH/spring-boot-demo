package com.example.demohello;

import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.demohello", "com.wyhcode"})
public class DemoHelloApplication {

    public static void main(String[] args) {
        Stopwatch time = Stopwatch.createStarted();

        SpringApplication.run(DemoHelloApplication.class, args);
        time.stop();
        log.info("项目启动耗时：{} s",time.elapsed().getSeconds());

    }

}
