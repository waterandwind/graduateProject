package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.config.Utils;
import com.restaurant.entity.*;
import com.restaurant.entity.requset.OrderListOptionDto;
import com.restaurant.entity.result.OrderDetail;
import com.restaurant.mapper.*;
import com.restaurant.service.IMainOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.service.IOrderListService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MainOrderServiceImpl extends ServiceImpl<MainOrderMapper, MainOrder> implements IMainOrderService {
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    OrderOptionListMapper optionRecordMapper;
    @Autowired
    IOrderListService iOrderListService;
    @Autowired
    OrderListMapper orderListMapper;
    @Autowired
    OrderOptionListMapper orderOptionListMapper;
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
            BigDecimal totalOpt = new BigDecimal(0);

            commodity.setUnitPrice(com.getSaleCost());
            commodity.setCommodityName(com.getCommodityName());
            //（售价）* 数量
            commodity.setTotalPrice(com.getSaleCost().add(totalOpt).multiply(new BigDecimal(commodity.getQuantity())));
            commodity.setMainOrderCode(orderNum);
            iOrderListService.save(commodity);

            totalPrice=totalPrice.add(commodity.getTotalPrice());
        }
        MainOrder order = new MainOrder();
        BeanUtils.copyProperties(list, order);
        order.setOrderCode(orderNum);
        order.setTotalPrice(totalPrice);
        order.setIsRead(1);
        save(order);
        QueryWrapper<MainOrder> qw= new QueryWrapper<>(order);
        OrderDetail rsOrder=new OrderDetail();
        BeanUtils.copyProperties(order, rsOrder);
        rsOrder.setOrderList(list.getOrderList());
        return rsOrder;
    }

    @Override
    public OrderDetail getOrderDetail(MainOrder order) {
        OrderDetail detail=new OrderDetail();
        QueryWrapper<MainOrder> qw= new QueryWrapper<>(order);
        qw.eq("order_code",order.getOrderCode());
        MainOrder mainOrder= getOne(qw);
        //复制结果给detail
        BeanUtils.copyProperties(mainOrder,detail);
        Map<String,Object> map=new HashMap<>();
        map.put("main_order_code",order.getOrderCode());
         detail.setOrderList(orderListMapper.selectByMap(map));
//        List<OrderList> orderList = new ArrayList<>();
//
//        OrderList orderList1=new OrderList();
//        orderList1.setMainOrderCode(mainOrder.getOrderCode());
//        QueryWrapper<OrderList> ol= new QueryWrapper<>(orderList1);
//        List<OrderList> rslist=orderListMapper.selectList(ol);
//        for (OrderList item:rslist
//             ) {
//            OrderListOptionDto olod=new OrderListOptionDto();
//            BeanUtils.copyProperties(item,olod);
//            List<Option> options=new ArrayList<>();
//            OrderOptionList orderOptionList=new OrderOptionList();
//            orderList1.setMainOrderCode(mainOrder.getOrderCode());
//            QueryWrapper<OrderOptionList> optionListQueryWrapper= new QueryWrapper<>(orderOptionList);
////            List<OrderList>  orderOptionLists=orderOptionListMapper.selectList(optionListQueryWrapper);
//            for (OrderOptionList ite:orderOptionLists
//                 ) {
//                Option option=new Option();
//                BeanUtils.copyProperties(ite,option);
//                options.add(option);
//            }
//            orderList.add(olod);
//

//        detail.setOrderList(orderList);
        return detail;
    }

}
