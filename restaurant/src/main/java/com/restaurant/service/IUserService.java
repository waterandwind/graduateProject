package com.restaurant.service;

import com.restaurant.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.restaurant.entity.result.UserDto;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
public interface IUserService extends IService<User> {
    /**
     * 新增用户
     *
     * @param user
     * @return
     */
    public boolean createUser(User user);

    /**
     * 是否存在账号
     *
     * @param account
     * @return
     */
    public boolean hasExist(String account);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    public UserDto login(User user);
}
