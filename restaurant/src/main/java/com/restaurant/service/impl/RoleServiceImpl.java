package com.restaurant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.AccountRole;
import com.restaurant.entity.Role;
import com.restaurant.entity.RoleDetail;
import com.restaurant.entity.User;
import com.restaurant.mapper.RoleMapper;
import com.restaurant.mapper.UserMapper;
import com.restaurant.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public RoleDetail getRoleDetail(Role role) {
        Role role1=roleMapper.selectById(role.getId());
        RoleDetail detail=new RoleDetail();
        BeanUtils.copyProperties(role1,detail);
        detail.setRights(roleMapper.getRoleDetail(role));
        return detail;
    }

    @Override
    public AccountRole getAccountRole(User user) {
        AccountRole rs= new AccountRole();
        BeanUtils.copyProperties(userMapper.selectById(user.getId()),rs);
        rs.setRoleList(roleMapper.getRoleList(user));
        return rs;
    }
}
