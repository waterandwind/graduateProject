package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.config.Utils;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.MainOrder;
import com.restaurant.entity.OrderList;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.mapper.CommodityMapper;
import com.restaurant.mapper.MainOrderMapper;
import com.restaurant.mapper.OrderListMapper;
import com.restaurant.service.IMainOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.service.IOrderListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements IMainOrderService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    IOrderListService iOrderListService;
    @Autowired
    StringRedisTemplate redis;
    @Override
    public OrderDetail createOrder(OrderDetail list) {
        //生成订单编码
        String orderNum = redis.opsForValue().increment("orderNum").toString();
        orderNum="0000"+orderNum;
        orderNum=orderNum.substring(orderNum.length()-5,orderNum.length());
        String  now=  LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        orderNum=now+orderNum;

        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderList commodity : list.getOrderList()
        ) {
            Commodity com = commodityMapper.selectById(commodity.getCommodityId());
            commodity.setUnitPrice(com.getSaleCost());
            commodity.setCommodityName(com.getCommodityName());
            commodity.setTotalPrice(com.getSaleCost().multiply(new BigDecimal(commodity.getQuantity())));
            commodity.setMainOrderCode(orderNum);
            totalPrice=totalPrice.add(commodity.getTotalPrice());
        }
        MainOrder order = new MainOrder();
        BeanUtils.copyProperties(list, order);

        order.setOrderCode(orderNum);
        order.setTotalPrice(totalPrice);
        order.setPayState(0);
        save(order);
        iOrderListService.saveBatch(list.getOrderList());
        QueryWrapper<MainOrder> qw= new QueryWrapper<>(order);
        OrderDetail rsOrder=new OrderDetail();
        BeanUtils.copyProperties(order, rsOrder);
        rsOrder.setOrderList(list.getOrderList());
        return rsOrder;
    }
}
