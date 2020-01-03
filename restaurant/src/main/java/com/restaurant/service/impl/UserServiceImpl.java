package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.config.Utils;
import com.restaurant.entity.User;
import com.restaurant.entity.result.UserDto;
import com.restaurant.mapper.UserMapper;
import com.restaurant.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public boolean createUser(User user) {
        user.setPassword(Utils.getMD5(user.getPassword()));
        return save(user);
    }

    @Override
    public boolean hasExist(String account) {
        User queryAccount = new User();
        queryAccount.setAccountCode(account);
        Wrapper<User> selectUser = new QueryWrapper<>(queryAccount);
        User rs = getOne(selectUser);
        if (rs == null) {
            return false;
        } else {
            return true;
        }

    }

    @Override
    public UserDto login(User user) {
        user.setPassword(Utils.getMD5(user.getPassword()));
        Wrapper<User> selectUser = new QueryWrapper<>(user);
        User rs = userMapper.selectOne(selectUser);
        UserDto rsDto = new UserDto();
        if(rs!=null){
            BeanUtils.copyProperties(rs, rsDto);
            return rsDto;
        }
        return null;
    }
}
