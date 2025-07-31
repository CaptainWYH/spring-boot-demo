package com.example.demohello;

import com.wyhcode.service.RedisBloomFilterService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author weiyuhui
 * @Date: 2025/8/1 00:10
 **/
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@ComponentScan(basePackages = {"com.example.demohello", "com.wyhcode"})
public class BloomTest{

    @Resource
    private RedisBloomFilterService redisBloomFilterService;

    @Test
    public void test() {
        // 预期插入数量
        long expectedInsertions = 10000L;
        // 错误比率
        double falseProbability = 0.01;
        redisBloomFilterService.initBloomFilter("bloom", expectedInsertions, falseProbability);

        // 向布隆过滤器添加元素
        for (int i = 0; i < expectedInsertions; i++){
            redisBloomFilterService.add("bloom", i + "");
        }
        long bloomCount = redisBloomFilterService.getBloomFilter("bloom").count();
        log.info("elementCount = {}.", bloomCount);

        // 统计误判次数
        int count = 0;
        for (long i = expectedInsertions; i < expectedInsertions * 2; i++) {
            if (redisBloomFilterService.getBloomFilter("bloom").contains(String.valueOf(i))) {
                count++;
            }
        }
        log.info("误判次数 = {}.", count);
        redisBloomFilterService.getBloomFilter("bloom").delete();
    }
}
