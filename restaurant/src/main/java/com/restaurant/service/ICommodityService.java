package com.restaurant.service;

import com.restaurant.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.requset.CommodityCreateDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface ICommodityService extends IService<Commodity> {

    public List<String> selectTypeList();

    public boolean createCommodity(CommodityCreateDto commodityCreateDto);
}
