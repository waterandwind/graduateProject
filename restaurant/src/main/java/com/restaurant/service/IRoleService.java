package com.restaurant.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.*;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zyw
 * @since 2020-03-10
 */
public interface IRoleService extends IService<Role> {
    RoleDetail getRoleDetail(Role role);
    AccountRole getAccountRole(User user);
    boolean updateAccoutRole(AccountRoleDto accountRoleDto);
    boolean updateRoleRight(RoleRightDto roleRightDto);
}
