package com.restaurant.controller;


import com.restaurant.entity.Commodity;
import com.restaurant.entity.MainOrder;
import com.restaurant.entity.result.Response;
import com.restaurant.service.IMainOrderService;
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
@RequestMapping("/main_order")
public class MainOrderController {
    @Autowired
    IMainOrderService iMainOrderService;


    @PostMapping
    @ApiOperation(value = "保存商品信息")
    public Response createOrder(@RequestBody MainOrder mainOrder) {
        boolean rs = iMainOrderService.save(mainOrder);
        if (rs){
            return Response.success("保存成功");
        }else {
            return Response.bizError("保存失败");
        }
    }
}

