package com.restaurant.service;

import com.restaurant.entity.Commodity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface ICommodityService extends IService<Commodity> {

    public List<String> selectTypeList();
}
