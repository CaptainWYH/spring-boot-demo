package com.wyhcode.service.impl;

import com.wyhcode.service.RedisBloomFilterService;
import io.lettuce.core.RedisClient;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weiyuhui
 * @Date: 2025/7/31 23:51
 **/
@Service
public class RedisBloomFilterServiceImpl implements RedisBloomFilterService {

    @Resource
    private RedissonClient redissonClient;

    private final Map<String, RBloomFilter<String> > bloomFilterMap = new HashMap<>();

    @Override
    public void initBloomFilter(String name, long expectedInsertions, double fpp) {
        RBloomFilter<String> bloomFilter = redissonClient.getBloomFilter(name);
        bloomFilter.tryInit(expectedInsertions, fpp);
        bloomFilterMap.put(name, bloomFilter);
    }

    @Override
    public void add(String name, String value) {
        RBloomFilter<String> filter = bloomFilterMap.get(name);
        if (filter != null){
            filter.add(value);
        }
    }

    @Override
    public boolean contains(String name, String value) {
        RBloomFilter<String> filter = bloomFilterMap.get(name);
        return filter != null && filter.contains(value);
    }

    @Override
    public RBloomFilter<String> getBloomFilter(String name) {
        return bloomFilterMap.get(name);
    }
}
