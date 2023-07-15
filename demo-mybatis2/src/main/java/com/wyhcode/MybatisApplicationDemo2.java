package com.wyhcode;


import com.google.common.base.Stopwatch;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MybatisApplicationDemo2 {
    public static void main(String[] args) {
        Stopwatch started = Stopwatch.createStarted();
        SpringApplication.run(MybatisApplicationDemo2.class,args);
        started.stop();
        log.info("项目启动耗时：{} s", started.elapsed().getSeconds());
        log.error("这是一个异常");
        log.warn("这是一个警告");

        // DO VO DTO
        //

    }
}
