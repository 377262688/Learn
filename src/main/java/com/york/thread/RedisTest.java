package com.york.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author york
 * @create 2019-10-19 21:56
 **/
@RestController
@RequestMapping("redisTest")
@Slf4j
public class RedisTest {
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("test")
    public void test() {
        redisTemplate.opsForValue().set("123","1");
        redisTemplate.expire("123", 1, TimeUnit.MINUTES);
        redisTemplate.opsForValue().increment("123");
//        ListOperations listOperations = redisTemplate.opsForList();
//        long time = System.currentTimeMillis();
//        for (int i = 0; i < 10000; i++) {
//            listOperations.leftPush("queue_1",i);
//        }
//        log.info("redis普通操作耗时{}",System.currentTimeMillis() - time);
//        time = System.currentTimeMillis();
//        List list = redisTemplate.executePipelined(new RedisCallback<String>() {
//            /**
//             * Gets called by {@link RedisTemplate} with an active Redis connection. Does not need to care about activating or
//             * closing the connection or handling exceptions.
//             *
//             * @param connection active Redis connection
//             * @return a result object or {@code null} if none
//             * @throws DataAccessException
//             */
//            @Override
//            public String doInRedis(RedisConnection connection) throws DataAccessException {
//                for (int i = 0; i < 1000; i++) {
//                    connection.lPush("queue_2".getBytes(),String.valueOf(i).getBytes());
//                }
//                return null;
//            }
//        });
//        log.info("redis pipeline操作耗时：{}",System.currentTimeMillis() - time);
//
//        GeoOperations geoOperations = redisTemplate.opsForGeo();
    }
}
