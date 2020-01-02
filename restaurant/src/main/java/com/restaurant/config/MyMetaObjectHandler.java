package com.restaurant.config;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

/**
 * 填充器
 *
 * @author nieqiurong 2018-08-10 22:59:23.
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        System.out.println(LocalDateTime.now());
        this.setFieldValByName("createDate", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.setFieldValByName("updateDate", LocalDateTime.now(), metaObject);
    }
}
