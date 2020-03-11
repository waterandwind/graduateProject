package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.Option;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.result.ParamLackException;
import com.restaurant.mapper.CommodityMapper;
import com.restaurant.mapper.OptionMapper;
import com.restaurant.service.ICommodityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.service.IOptionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
@Transactional(rollbackFor=Exception.class)
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
    public boolean updateCommodity(Commodity commodity) {
        boolean rs=updateById(commodity);
//        Option option=new Option();
//        option.setCommodityCode(commodityCode);
//        QueryWrapper<Option> qw= new QueryWrapper<>(option);
//        iOptionService.remove(qw);
//        for (Option item:
//                commodityCreateDto.getOptions()) {
//            item.setCommodityCode(commodityCode);
//        }

        return rs;
    }

    @Override
    public boolean removeCommodity(List<String> commodityCode) throws Exception {
        if (commodityCode.size()!=0){
            Map<String, Object> map = new HashMap<>();
            for (String code:commodityCode
                 ) {
                map.put("commodity_code",code);
                removeByMap(map);
                 return iOptionService.removeByMap(map);
            }
            return false;
        }else {
            throw new ParamLackException("CommodityCode缺失");
        }
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
