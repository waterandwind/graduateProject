package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.requset.CommodityPageDto;
import com.restaurant.mapper.CommodityMapper;
import com.restaurant.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
public class CommodityServiceImpl extends ServiceImpl<CommodityMapper, Commodity> implements ICommodityService {
    @Autowired
    CommodityMapper commodityMapper;

    @Override
    public List<String> selectTypeList() {
        return commodityMapper.getTypeList();
    }

//    public List<Commodity> selectCommodityList(CommodityPageDto commodity){
//       commodityMapper.selectPage()
//    }
}
