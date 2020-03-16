package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.restaurant.config.Utils;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.Right;
import com.restaurant.entity.User;
import com.restaurant.entity.requset.Page;
import com.restaurant.entity.result.Response;
import com.restaurant.entity.result.ResponseStatus;
import com.restaurant.entity.result.UserDto;
import com.restaurant.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService iUserService;

    @Autowired
    private StringRedisTemplate redis;

    @PostMapping
    @ApiOperation(value = "新增账号")
    public Response createUser(@RequestBody User user) {
        if (iUserService.hasExist(user.getAccountCode())) {
            return Response.bizError("注册失败，账号已被注册");
        }
        boolean rs = iUserService.createUser(user);
        if (rs) {
            return Response.success("新增成功");
        } else {
            return Response.bizError("新增失败");
        }
    }

    @PostMapping("login")
    @ApiOperation(value = "后台登录")
    public Response login(@RequestBody User user) {

        UserDto rs = iUserService.login(user);
        if (rs != null) {
            String token = Utils.uuidStr();
            redis.opsForValue().set(token, user.getAccountCode(), 30000, TimeUnit.SECONDS);
            rs.setToken(token);
            return Response.success("登录成功", rs);
        } else {
            return Response.accountOrPasswordError("账号或者密码错误");
        }
    }

    @PostMapping("logout")
    @ApiOperation(value = "注销")
    public Response logout(@RequestBody UserDto user) {
        String rs = redis.opsForValue().get(user.getToken());
        if (rs != null) {
            redis.opsForValue().getOperations().delete(user.getToken());
            return Response.success("退出完成");
        } else {
            return Response.bizError("token失效");
        }
    }

    @PostMapping("testToken")
    @ApiOperation(value = "测试token存活")
    public Response testToken(UserDto user, HttpServletRequest request) {
        String rs = redis.opsForValue().get(request.getHeader("token"));
        if (rs != null) {
            return Response.success("token存在", rs);
        } else {
            return Response.bizError("token失效，请重新登录");
        }
    }
    @GetMapping("addAccount")
    @ApiOperation(value = "增加后台账号")
    public Response addAccount(Integer num) {
        List<User> rs =iUserService.addAccount(num);
        if (rs != null&&rs.size()>0) {
            return Response.success("创建完毕", rs);
        } else  {
            return Response.bizError("创建出错");
        }
    }

    @PostMapping("updateAccount")
    @ApiOperation(value = "修改后账号")
    public Response addAccount(@RequestBody User user) {
        boolean rs =iUserService.updateAccount(user);
        if (rs) {
            return Response.success("修改完毕",user);
        } else  {
            return Response.bizError("修改错误");
        }
    }

    @GetMapping("exist")
    @ApiOperation(value = "账号是否存在")
    public Response hasExist(String accountCode) {
        if (iUserService.hasExist(accountCode)) {
            return Response.success("账号不存在");
        } else {
            return Response.bizError("账号存在");
        }

    }

    @GetMapping("accountList")
    @ApiOperation(value = "后台账号列表")
    public Response rightList(@Valid Page page) {
        QueryWrapper<User> qw=new QueryWrapper<>();
        qw.eq("type",1);
        IPage rs= iUserService.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<User>(page.getCurrent(),page.getPageSize()),qw);
        if (rs!=null) {
            return Response.success("查询成功",rs);
        } else {
            return Response.notFound("查询失败");
        }
    }



}

