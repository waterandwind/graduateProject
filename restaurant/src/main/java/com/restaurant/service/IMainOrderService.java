package com.restaurant.service;

import com.restaurant.entity.MainOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.OrderList;
import com.restaurant.entity.SaleCountModel;
import com.restaurant.entity.result.OrderDetail;

import java.time.LocalDateTime;
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

    /**
     * 订单详情
     *
     * @param order
     * @return
     */
    public OrderDetail getOrderDetail(MainOrder order);

    /**
     * 订单销售额统计
     *
     * @param startDate,endDate
     * @return
     */
    public List<SaleCountModel> getSaleCount(LocalDateTime startDate,LocalDateTime endDate);
}
