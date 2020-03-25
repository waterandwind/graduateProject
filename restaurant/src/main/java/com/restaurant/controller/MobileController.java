package com.restaurant.controller;

import com.restaurant.entity.CommodityWithType;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.entity.result.Response;
import com.restaurant.service.ICommodityService;
import com.restaurant.service.IMainOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/mobile")
public class MobileController {
    @Autowired
    ICommodityService iCommodeytService;

    @Autowired
    IMainOrderService iMainOrderService;

    @PostMapping
    @ApiOperation(value = "保存订单")
    public Response createOrder(@RequestBody OrderDetail orderDetail) {
        orderDetail.setIsRead(0);
        OrderDetail rs = iMainOrderService.createOrder(orderDetail);
        if (rs!=null) {
            return Response.success("保存成功",rs);
        } else {
            return Response.bizError("保存失败");
        }
    }
    @GetMapping
    @ApiOperation(value = "获取商品类型列表及商品")
    public Response getCommodity() {
        CommodityWithType rs=new CommodityWithType();
        rs.setCommodityList(iCommodeytService.getCommodityList());
        rs.setTypeList(iCommodeytService.selectTypeList());

            return Response.success("查询完毕",rs);

    }

}
