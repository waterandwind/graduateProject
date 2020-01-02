package com.restaurant.service.impl;

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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    @Override
    public OrderDetail createOrder(OrderDetail list) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderList commodity : list.getOrderList()
        ) {
            Commodity com = commodityMapper.selectById(commodity.getCommodityId());
            commodity.setUnitPrice(com.getSaleCost());
            commodity.setCommodityName(com.getCommodityName());
            commodity.setTotalPrice(com.getSaleCost().multiply(new BigDecimal(commodity.getQuantity())));
            totalPrice.add(commodity.getTotalPrice());
        }
        MainOrder order = new MainOrder();
        BeanUtils.copyProperties(list, order);
        save(order);
        iOrderListService.saveBatch(list.getOrderList());
        return null;
    }
}
