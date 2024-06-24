package com.shoppingwebapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setStr(String key, String value) {
        long timeout = 30;                  //時間
        TimeUnit unit = TimeUnit.SECONDS; //時間單位
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String getStr(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
