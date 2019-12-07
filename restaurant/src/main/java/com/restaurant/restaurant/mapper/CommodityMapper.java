package com.restaurant.restaurant.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CommodityMapper {
    @Select("select count(*) from commodity")
    int count();
}
