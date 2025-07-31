package com.wyhcode.service;

import org.redisson.api.RBloomFilter;

/**
 * @author Wei YuHui
 * @Date: 2025/7/31 23:51
 **/
public interface RedisBloomFilterService {

    void initBloomFilter(String name, long expectedInsertions, double fpp);

    void add(String name, String value);

    boolean contains(String name, String value);

    RBloomFilter<String> getBloomFilter(String name);
}
