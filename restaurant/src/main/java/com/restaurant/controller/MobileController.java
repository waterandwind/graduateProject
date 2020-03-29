package com.restaurant.controller;

import com.restaurant.entity.CommodityWithType;
import com.restaurant.entity.MainOrder;
import com.restaurant.entity.User;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.entity.result.Response;
import com.restaurant.service.ICommodityService;
import com.restaurant.service.IMainOrderService;
import com.restaurant.service.IUserRoleService;
import com.restaurant.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/mobile")
public class MobileController {
    @Autowired
    ICommodityService iCommodeytService;

    @Autowired
    IMainOrderService iMainOrderService;

    @Autowired
    IUserService iUserService;
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
    @GetMapping("getList")
    @ApiOperation(value = "获取用户订单列表")
    public Response getOrderList(String account) {
        List<MainOrder> rs = iMainOrderService.getUserOrder(account);
        if (rs!=null) {
            return Response.success("查找完毕",rs);
        } else {
            return Response.bizError("未找到订单");
        }
    }
    @GetMapping("getOrderDetail")
    @ApiOperation(value = "获取订单详情")
    public Response getOrderDetail(String orderCode) {
        MainOrder mainOrder=new MainOrder();
        mainOrder.setOrderCode(orderCode);
        OrderDetail rs = iMainOrderService.getOrderDetail(mainOrder);
        if (rs!=null) {
            return Response.success("查找成功",rs);
        } else {
            return Response.bizError("查找失败");
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
    @PostMapping("addUser")
    @ApiOperation(value = "新增账号")
    public Response createUser(@RequestBody User user) {
        if (iUserService.hasExist(user.getAccountCode())) {
            return Response.bizError("注册失败，账号已被注册");
        }
        boolean rs = iUserService.createUser(user);
        if (rs) {
            return Response.success("新增成功");
        } else {
            return Response.bizError("新增失败");
        }
    }
    @PostMapping("updateUser")
    @ApiOperation(value = "修改账号")
    public Response updateUser(@RequestBody User user) {

        boolean rs = iUserService.updateAccount(user);
        if (rs) {
            return Response.success("修改成功");
        } else {
            return Response.bizError("修改失败");
        }
    }
}
