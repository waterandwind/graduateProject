package com.restaurant.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.restaurant.entity.*;
import com.restaurant.mapper.RoleMapper;
import com.restaurant.mapper.RoleRightMapper;
import com.restaurant.mapper.UserMapper;
import com.restaurant.mapper.UserRoleMapper;
import com.restaurant.service.IRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleRightMapper roleRightMapper;
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

    @Override
    public boolean updateAccoutRole(AccountRoleDto accountRoleDto) {
        List<UserRole> userRoleList=new ArrayList<>();
        for (Integer id:
             accountRoleDto.getRightList()) {
            UserRole tem=new UserRole();
            tem.setAccountCode(accountRoleDto.getAccountCode());
            tem.setRoleId(id);
            userRoleList.add(tem);
        }
        Map<String,Object> map= new HashMap<>();
        map.put("account_code",accountRoleDto.getAccountCode());
        userRoleMapper.deleteByMap(map);
        for (UserRole userRole:
               userRoleList) {
           userRoleMapper.insert(userRole);
        }
        return true;
    }

    @Override
    public boolean updateRoleRight(RoleRightDto roleRightDto) {
        List<RoleRight> roleRights=new ArrayList<>();
        for (Integer id:
                roleRightDto.getRightList()) {
            RoleRight tem=new RoleRight();
            tem.setRoleId(roleRightDto.getRoleId());
            tem.setRightId(id);
            roleRights.add(tem);
        }
        Role role=new Role();
        role.setId(roleRightDto.getRoleId());
        role.setRoleName(roleRightDto.getRoleName());
        roleMapper.updateById(role);
        Map<String,Object> map= new HashMap<>();
        map.put("role_id",roleRightDto.getRoleId());
        roleRightMapper.deleteByMap(map);
        for (RoleRight roleRight:
                roleRights) {
            roleRightMapper.insert(roleRight);
        }
        return true;
    }
}
