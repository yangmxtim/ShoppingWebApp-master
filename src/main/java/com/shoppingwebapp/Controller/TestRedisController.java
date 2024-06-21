package com.shoppingwebapp.Controller;

import com.shoppingwebapp.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {

    private final RedisService redisService;

    @Autowired
    public TestRedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    //set key
    @PostMapping("/set")
    public String set(@RequestParam String key, @RequestParam String value) {
        redisService.setStr(key, value);
        return "Set key " + key + " with value " + value;
    }

    @GetMapping("/get")
    public String get(@RequestParam String key) {
        String value = redisService.getStr(key);
        return "Value for key " + key + " is " + value;
    }
}
