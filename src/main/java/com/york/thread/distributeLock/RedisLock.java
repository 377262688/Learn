package com.york.thread.distributeLock;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * redis 分布式锁
 * setnx ex
 * @author york
 * @create 2020-10-10 22:02
 **/
public class RedisLock implements Closeable {

    private RedisTemplate redisTemplate;

    private String lockKey;

    private static String lockValue;

    @Override
    public void close() throws IOException {
        redisTemplate.delete(lockKey);
    }

    public Boolean lock() {
        return redisTemplate.opsForValue().setIfPresent(lockKey,lockValue,1000, TimeUnit.MILLISECONDS);
    }
}
