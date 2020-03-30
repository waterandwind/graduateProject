package com.restaurant.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.restaurant.config.Utils;
import com.restaurant.entity.User;
import com.restaurant.entity.result.UserDto;
import com.restaurant.mapper.RoleMapper;
import com.restaurant.mapper.UserMapper;
import com.restaurant.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    RoleMapper roleMapper;

    @Autowired
    StringRedisTemplate redis;

    @Override
    public boolean createUser(User user) {
        user.setPassword(Utils.getMD5(user.getPassword()));
        user.setType(0);
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
    public List<User> addAccount(Integer num) {
        List<User> userList=new ArrayList<>();
        for (int i=0;i<num;i++){
            User item= new User();
            item.setName("未知用户");
            String code=redis.opsForValue().increment("accountCode").toString();
            //账号存在则让循环多执行一次，保证生成账号数量正确
            if (hasExist(code)){
                i--;
                //跳出本次循环,
                continue;
            }else {
                item.setAccountCode(code);
            }
            item.setAccountCode(code);
            item.setType(1);
            item.setPassword(Utils.getMD5("123456"));
            userList.add(item);
        }
        boolean rs=saveBatch(userList);
        if (rs){
            return userList;
        }else {
            return null;
        }
    }

    @Override
    public boolean updateAccount(User user) {
        if (user.getPassword()!= null&&user.getPassword()!=""){
           user.setPassword(Utils.getMD5(user.getPassword()));
        }
        if (user.getPassword()!=null&&user.getPassword()==""){
            user.setPassword(null);
        }
        if (userMapper.updateById(user)>0){
            return true;
        }
        return false;
    }

    @Override
    public UserDto login(User user) {
        user.setPassword(Utils.getMD5(user.getPassword()));
        Wrapper<User> selectUser = new QueryWrapper<>(user);
        User rs = userMapper.selectOne(selectUser);
        UserDto rsDto = new UserDto();
        if(rs!=null&&rs.getType()==user.getType()){
            String token = Utils.uuidStr();
            redis.opsForValue().set(token, user.getAccountCode(), 30000, TimeUnit.SECONDS);
            rsDto.setToken(token);
            List<String> rightList=roleMapper.getRightList(user.getAccountCode());
            if (rightList!=null&&rightList.size()>0){
                redis.opsForList().leftPushAll("r"+user.getAccountCode(),rightList);
            }
            BeanUtils.copyProperties(rs, rsDto);
            return rsDto;
        }
        return null;
    }
}
