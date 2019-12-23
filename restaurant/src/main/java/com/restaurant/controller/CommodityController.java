package com.restaurant.controller;


import com.restaurant.entity.Commodity;
import com.restaurant.service.ICommodityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    ICommodityService icommodeytService;


    @PostMapping("saveCommodity")
    @ApiOperation(value = "保存商品")
    public boolean saveCommodity(@RequestBody(required=true) Commodity commodity)  throws Exception{
        return   icommodeytService.save(commodity);
    }
}

