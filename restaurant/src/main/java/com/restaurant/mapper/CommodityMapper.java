package com.restaurant.mapper;

import com.restaurant.entity.Commodity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface CommodityMapper extends BaseMapper<Commodity> {
    public List<String> getTypeList();
}
