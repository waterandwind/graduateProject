package com.restaurant.mapper;

import com.restaurant.entity.CommoditySaleMode;
import com.restaurant.entity.MainOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.SaleCountModel;
import com.restaurant.entity.TimeOrderCountDto;
import org.apache.ibatis.annotations.Param;

import javax.activation.CommandObject;
import java.time.LocalDateTime;
import java.util.List;

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
    List<CommoditySaleMode> getCommoditySaleMode(@Param("date1")String date1, @Param("date2")String date2);
    Integer getTimeOrderCount(@Param("date1")String date1, @Param("date2")String date2);
}
