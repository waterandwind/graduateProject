package com.restaurant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.OrderOptionList;
import com.restaurant.mapper.OrderOptionListMapper;
import com.restaurant.service.IOrderOptionListService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyw
 * @since 2020-01-21
 */
@Service
public class OrderOptionListServiceImpl extends ServiceImpl<OrderOptionListMapper, OrderOptionList> implements IOrderOptionListService {

}
