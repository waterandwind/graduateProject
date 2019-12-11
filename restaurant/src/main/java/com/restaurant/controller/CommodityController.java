package com.restaurant.controller;


import com.restaurant.mapper.CommodityMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/music")
public class CommodityController {
    @Autowired
    CommodityMapper commodityMapper;

    @GetMapping("test")
    @ApiOperation(value = "测试")
    public Integer firstPartyHome(){
        int a =commodityMapper.test();
        return a;
    }
}
