package com.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
    @Autowired
    StringRedisTemplate redis;

    /**
     * 重置或创建订单数
     *
     * @param
     * @return
     */
    @Scheduled(cron = "0 0 0 ? * *")
    public void reasetOrderNum(){
        redis.opsForValue().set("orderNum","0");
        System.out.println("orderNum重置");
    }
}
