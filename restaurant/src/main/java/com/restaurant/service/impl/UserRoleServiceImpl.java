package com.restaurant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.UserRole;
import com.restaurant.mapper.UserRoleMapper;
import com.restaurant.service.IUserRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zyw
 * @since 2020-03-10
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

}
