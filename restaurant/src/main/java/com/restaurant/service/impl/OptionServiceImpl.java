package com.restaurant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.Option;
import com.restaurant.mapper.OptionMapper;
import com.restaurant.service.IOptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyw
 * @since 2020-01-03
 */
@Service
public class OptionServiceImpl extends ServiceImpl<OptionMapper, Option> implements IOptionService {

}
