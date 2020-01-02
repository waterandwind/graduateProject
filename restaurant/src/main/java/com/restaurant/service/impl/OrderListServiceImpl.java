package com.restaurant.service.impl;

import com.restaurant.entity.OrderList;
import com.restaurant.mapper.OrderListMapper;
import com.restaurant.service.IOrderListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
public class OrderListServiceImpl extends ServiceImpl<OrderListMapper, OrderList> implements IOrderListService {

}
