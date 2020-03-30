package com.restaurant.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.restaurant.entity.Right;
import com.restaurant.entity.Role;
import com.restaurant.entity.RoleDetail;
import com.restaurant.entity.User;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zyw
 * @since 2020-03-10
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Right> getRoleDetail(Role role);
    List<Role> getRoleList(User user);
    List<String> getRightList(String accout);

}
