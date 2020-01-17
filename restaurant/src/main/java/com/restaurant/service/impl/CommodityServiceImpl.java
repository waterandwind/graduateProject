package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.MainOrder;
import com.restaurant.entity.Option;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.requset.CommodityPageDto;
import com.restaurant.mapper.CommodityMapper;
import com.restaurant.mapper.OptionMapper;
import com.restaurant.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.service.IOptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.convert.EntityWriter;
import org.springframework.data.redis.core.StringRedisTemplate;
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
    @Autowired
    OptionMapper optionMapper;
    @Autowired
    IOptionService iOptionService;
    @Autowired
    StringRedisTemplate redis;
    @Override
    public List<String> selectTypeList() {
        return commodityMapper.getTypeList();
    }

    @Override
    public boolean createCommodity(CommodityCreateDto commodityCreateDto) {
        Commodity saveCommodity=new Commodity();
        BeanUtils.copyProperties(commodityCreateDto,saveCommodity);
        String commodityCode="COM"+redis.opsForValue().increment("commodityNum").toString();
        //默认下架
        saveCommodity.setState(0);
        saveCommodity.setCommodityCode(commodityCode);
        boolean saveCom=save(saveCommodity);
        for (Option option:
             commodityCreateDto.getOptions()) {
           option.setCommodityCode(commodityCode);
        }
        boolean saveOption=iOptionService.saveBatch(commodityCreateDto.getOptions());
        if (saveCom&&saveOption){
            return true;
        }else {
            return false;
        }

    }

    @Override
    public boolean updateCommodity(CommodityCreateDto commodityCreateDto) {
        Commodity saveCommodity=new Commodity();
        BeanUtils.copyProperties(commodityCreateDto,saveCommodity);
        String commodityCode=saveCommodity.getCommodityCode();
        boolean rs=updateById(saveCommodity);
        Option option=new Option();
        option.setCommodityCode(commodityCode);
        QueryWrapper<Option> qw= new QueryWrapper<>(option);
        iOptionService.remove(qw);
        for (Option item:
                commodityCreateDto.getOptions()) {
            item.setCommodityCode(commodityCode);
        }
        iOptionService.saveBatch(commodityCreateDto.getOptions());
        return rs;
    }

    @Override
    public CommodityCreateDto getCommodity(Commodity commodity) {
        CommodityCreateDto rs=new CommodityCreateDto();
        Commodity commodityRs=getById(commodity.getId());
        BeanUtils.copyProperties(commodityRs,rs);
        Option query=new Option();
        query.setCommodityCode(commodityRs.getCommodityCode());
        QueryWrapper<Option> qw= new QueryWrapper<>(query);
        rs.setOptions(optionMapper.selectList(qw));
        return rs;
    }

//    public List<Commodity> selectCommodityList(CommodityPageDto commodity){
//       commodityMapper.selectPage()
//    }
}
