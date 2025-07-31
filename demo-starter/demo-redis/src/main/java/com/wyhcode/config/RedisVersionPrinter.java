package com.wyhcode.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
@ConditionalOnBean(RedisConnectionFactory.class)
public class RedisVersionPrinter implements CommandLineRunner {

    private final RedisConnectionFactory redisConnectionFactory;

    public RedisVersionPrinter(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Override
    public void run(String... args) throws Exception {
        String version = Objects.requireNonNull(redisConnectionFactory.getConnection()
                        .info("server"))
                .getProperty("redis_version");
        
        System.out.println("\n============== Redis版本 ==============");
        System.out.println("| Redis Version: " + version + "          |");
        System.out.println("======================================\n");
    }
}