package com.restaurant.mapper;

import com.restaurant.entity.MainOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.SaleCountModel;

import java.time.LocalDateTime;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface MainOrderMapper extends BaseMapper<MainOrder> {
    SaleCountModel getSaleCount(String date);
}
