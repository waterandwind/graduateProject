package com.restaurant;

import com.restaurant.config.UuidUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println( UuidUtils.reName("dsfsdfds.jpg"));
    }
}
