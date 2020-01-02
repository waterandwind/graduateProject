package com.restaurant;

import com.restaurant.config.Utils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class RestaurantApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
//        stringRedisTemplate.opsForValue().set("a","test");
        System.out.println(stringRedisTemplate.opsForValue().getOperations().delete("a"));
    }
    @Test
    public void testOrderNum() {
        Utils.getOrderCode();
    }
}
