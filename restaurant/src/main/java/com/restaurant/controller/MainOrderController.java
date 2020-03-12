package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.MainOrder;
import com.restaurant.entity.OrderList;
import com.restaurant.entity.OrderListPageDto;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.entity.result.Response;
import com.restaurant.service.IMainOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
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
    @ApiOperation(value = "保存订单")
    public Response createOrder(@RequestBody OrderDetail orderDetail) {
        OrderDetail rs = iMainOrderService.createOrder(orderDetail);
        if (rs!=null) {
            return Response.success("保存成功",rs);
        } else {
            return Response.bizError("保存失败");
        }
    }
    @PostMapping("batchReadOrder")
    @ApiOperation(value = "批量已读订单")
    public Response batchReadOrder(@RequestBody List<Integer> idList) {
        List<MainOrder> orderList=new ArrayList<>();
        for (Integer id:
             idList) {
            MainOrder tem=new MainOrder();
            tem.setId(id);
            tem.setIsRead(1);
            orderList.add(tem);
        }
        boolean rs = iMainOrderService.updateBatchById(orderList);
        if (rs) {
            return Response.success("状态修改成功",rs);
        } else {
            return Response.bizError("状态修改成功");
        }
    }

    @GetMapping
    @ApiOperation(value = "查看订单详情")
    public Response createOrder( MainOrder mainOrder) {
        OrderDetail rs = iMainOrderService.getOrderDetail(mainOrder);
        if (rs!=null) {
            return Response.success("保存成功",rs);
        } else {
            return Response.bizError("保存失败");
        }
    }
    @GetMapping("orderList")
    @ApiOperation(value = "查看订单列表")
    public Response getOrderList( OrderListPageDto listDto) {
        MainOrder order = new MainOrder();
        order.setIsRead(listDto.getIsRead());
        QueryWrapper<MainOrder> qw = new QueryWrapper<>(order);
        if (listDto.getOrderCode()!=null){
            qw.like("order_code",listDto.getOrderCode());
        }
        if (listDto.getIsRead()!=null){
            qw.like("is_read",listDto.getIsRead());
        }
        qw.orderByDesc("create_date");
        IPage rs = iMainOrderService.page(new Page<MainOrder>(listDto.getCurrent(), listDto.getPageSize()), qw);
        return Response.success("查找完毕", rs);
    }


//    @GetMapping("/saleCount")
//    @ApiOperation(value = "销售额统计")
//    public Response getSaleCount(LocalDateTime startTime, LocalDateTime endTime) {
//
//
//        return Response.success("查找完毕", rs);
//    }
}

