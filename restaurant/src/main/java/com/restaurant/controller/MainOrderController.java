package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.entity.*;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.entity.result.Response;
import com.restaurant.service.IMainOrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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


    @PostMapping("saveOrder")
    @ApiOperation(value = "保存订单")
    public Response createOrder(@RequestBody OrderDetail orderDetail) {
        orderDetail.setIsRead(1);
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
    @PostMapping("batchDelete")
    @ApiOperation(value = "批量删除订单")
    public Response batchDelete(@RequestBody List<Integer> idList) {
        boolean rs = iMainOrderService.removeByIds(idList);
        if (rs) {
            return Response.success("删除完毕 ",rs);
        } else {
            return Response.bizError("删除出错");
        }
    }
    @GetMapping("getOrder")
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


    @GetMapping("/saleCount")
    @ApiOperation(value = "销售额统计")
    public Response getSaleCount(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(date+"-01 00:00:00",df);
        LocalDateTime   endTime=date1.plusMonths(1);
        List<SaleCountModel> rs= iMainOrderService.getSaleCount(date1,endTime);
        return Response.success("查找完毕", rs);
    }
    @GetMapping("/commoditySaleCount")
    @ApiOperation(value = "商品数统计")
    public Response commoditySaleCount(String date) {

        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(date+"-01 00:00:00",df);
//        LocalDateTime date=LocalDateTime.now();
        LocalDateTime   endTime=date1.plusMonths(1);
        List<CommoditySaleMode> rs= iMainOrderService.getCommoditySaleMode(date1,endTime);
        return Response.success("查找完毕", rs);
    }

    @GetMapping("/timeOrderCount")
    @ApiOperation(value = "时段统计")
    public Response timeOrderCount(String date) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime date1 = LocalDateTime.parse(date+" 00:00:00",df);
//        LocalDateTime date=LocalDateTime.now();
        LocalDateTime   endTime=date1.plusMonths(1);
        List<TimeOrderCountDto> rs= iMainOrderService.getTiemOrderCount(date1);
        return Response.success("查找完毕", rs);
    }
}

