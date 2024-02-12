package com.lrpcn.quickdev.manager.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 功能:
 * 作者: lrpcn
 * 日期: 2024/2/9 0:24
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 设置Redis中的键值对
     *
     * @param key   要存入Redis的键
     * @param value 要存入Redis的值
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 在Redis缓存中使用指定键和过期时间（以秒为单位）设置值。
     *
     * @param key   用于在Redis中存储值的键
     * @param value 要存储在Redis中的值
     * @param ex    过期时间，单位为秒
     */
    public void set(String key, String value,int ex) {
        redisTemplate.opsForValue().set(key, value ,ex ,TimeUnit.SECONDS);
    }

    /**
     * 将指定键值对存入Redis缓存，并设置过期时间。
     *
     * @param key   要存入Redis的键
     * @param value 要存入Redis的值
     * @param ex    过期时间的数量，单位为timeUnit
     * @param timeUnit 过期时间的单位
     */
    public void set(String key, String value, int ex, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, ex, timeUnit);
    }

    public String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
