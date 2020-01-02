package com.restaurant.service;

import com.restaurant.entity.MainOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.OrderList;
import com.restaurant.entity.result.OrderDetail;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface IMainOrderService extends IService<MainOrder> {
    /**
     * 创建订单
     *
     * @param list
     * @return
     */
    public OrderDetail createOrder(OrderDetail list);
}
