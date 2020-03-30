package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.restaurant.entity.*;
import com.restaurant.entity.requset.BatchUpdateCommDto;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.requset.Page;
import com.restaurant.entity.result.Response;
import com.restaurant.service.IRightService;
import com.restaurant.service.IRoleRightService;
import com.restaurant.service.IRoleService;
import com.restaurant.service.IUserRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zyw
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    IRoleService iRoleService;
    @Autowired
    IRightService iRightService;
    @Autowired
    IUserRoleService iUserRoleService;
    @Autowired
    IRoleRightService iRoleRightService;

    @PostMapping("saveRole")
    @ApiOperation(value = "新建角色")
    public Response createRole(@RequestBody Role role) {
        boolean rs = iRoleService.save(role);
        if (rs) {
            return Response.success("保存成功");
        } else {
            return Response.bizError("保存失败");
        }
    }
    @PostMapping("right")
    @ApiOperation(value = "增加权限")
    public Response createRight(@RequestBody Right right) {
        boolean rs = iRightService.save(right);
        if (rs) {
            return Response.success("保存权限成功");
        } else {
            return Response.bizError("保存权限失败");
        }
    }

    @GetMapping("getRole")
    @ApiOperation(value = "查询角色详情")
    public Response roleDetail(@Valid Role role) {
        RoleDetail rs = iRoleService.getRoleDetail(role);
        if (rs!=null) {
            return Response.success("查询成功",rs);
        } else {
            return Response.notFound("查询失败");
        }
    }
    @GetMapping("rightList")
    @ApiOperation(value = "查询权限列表")
    public Response rightList(@Valid Page page) {
        IPage rs= iRightService.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Right>(page.getCurrent(),page.getPageSize()),null);
        if (rs!=null) {
            return Response.success("查询成功",rs);
        } else {
            return Response.notFound("查询失败");
        }
    }
    @GetMapping("roleList")
    @ApiOperation(value = "查询角色列表")
    public Response roleList(@Valid RolePageDto page) {
        QueryWrapper<Role> qw=new QueryWrapper<>();
        if (page.getRoleName()!=null){
            qw.like("role_name",page.getRoleName());
        }
        if (page.getStatus()!=null){
            qw.like("status",page.getStatus());
        }
        IPage rs= iRoleService.page(new com.baomidou.mybatisplus.extension.plugins.pagination.Page<Role>(page.getCurrent(),page.getPageSize()),qw);
        if (rs!=null) {
            return Response.success("查询成功",rs);
        } else {
            return Response.notFound("查询失败");
        }
    }
    @GetMapping("accountRoleList")
    @ApiOperation(value = "查询账号及角色")
    public Response accountRoleList(@Valid User user) {
        AccountRole rs= iRoleService.getAccountRole(user);
        if (rs!=null) {
            return Response.success("查询成功",rs);
        } else {
            return Response.notFound("查询失败");
        }
    }
    @PostMapping("addRoleToAccount")
    @ApiOperation(value = "账号分配角色")
    public Response addRoleToAccount(@RequestBody AccountRoleDto accountRoleDto) {
        boolean rs = iRoleService.updateAccoutRole(accountRoleDto);
        if (rs) {
            return Response.success("角色分配成功");
        } else {
            return Response.bizError("角色分配失败");
        }
    }
    @PostMapping("addRightToRole")
    @ApiOperation(value = "角色增加权限")
    public Response addRightToRole(@RequestBody RoleRightDto roleRightDto) {
        boolean rs = iRoleService.updateRoleRight(roleRightDto);
        if (rs) {
            return Response.success("角色增加权限成功");
        } else {
            return Response.bizError("角色增加权限失败");
        }
    }
    @PostMapping("roleStatusUpdate")
    @ApiOperation(value = "角色状态修改")
    public Response roleStatusUpdate(@RequestBody Role role) {
        boolean rs = iRoleService.updateById(role);
        if (rs) {
            return Response.success("角色状态修改成功");
        } else {
            return Response.bizError("角色状态修改失败");
        }
    }
    @PostMapping("editRight")
    @ApiOperation(value = "编辑权限")
    public Response roleStatusUpdate(@RequestBody Right right) {
        boolean rs = iRightService.updateById(right);
        if (rs) {
            return Response.success("权限修改成功");
        } else {
            return Response.bizError("权限修改失败");
        }
    }


    @PostMapping("batchStateUpdate")
    @ApiOperation(value = "状态修改")
    public Response batchUpComm(@RequestBody BatchStateDto dto) throws Exception {
        List<Role> list=new ArrayList<>();
        for (Integer id:
                dto.getIdList()) {
            Role role=new Role();
            role.setStatus(dto.getFlag());
            role.setId(id);
            list.add(role);
        }
        boolean rs = iRoleService.saveOrUpdateBatch(list);
        if (rs) {
            return Response.success("操作成功");
        } else {
            return Response.bizError("操作失败");
        }
    }
}

