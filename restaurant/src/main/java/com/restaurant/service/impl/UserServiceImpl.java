package com.restaurant.service.impl;

import com.restaurant.entity.User;
import com.restaurant.mapper.UserMapper;
import com.restaurant.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
