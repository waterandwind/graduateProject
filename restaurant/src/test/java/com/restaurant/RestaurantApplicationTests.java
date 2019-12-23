package com.restaurant;

import com.restaurant.mapper.CommodityMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationTests {

    @Autowired
    CommodityMapper commodityMapper;
    @Test
    void contextLoads() {
        System.out.println(commodityMapper.selectById(1));
    }

}