package com.restaurant.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.restaurant.config.Utils;
import com.restaurant.entity.Commodity;
import com.restaurant.entity.requset.CommodityCreateDto;
import com.restaurant.entity.requset.CommodityPageDto;
import com.restaurant.entity.result.Response;
import com.restaurant.service.ICommodityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.*;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@RestController
@RequestMapping("/commodity")
public class CommodityController {
    @Autowired
    ICommodityService iCommodeytService;


    @PostMapping
    @ApiOperation(value = "保存商品信息")
    public Response createCommodity(@RequestBody CommodityCreateDto commodity) {
        boolean rs = iCommodeytService.createCommodity(commodity);
        if (rs) {
            return Response.success("保存成功");
        } else {
            return Response.bizError("保存失败");
        }
    }

    @PatchMapping
    @ApiOperation(value = "修改商品信息")
    public Response updateCommodity(Commodity commodity) {
        boolean rs = iCommodeytService.updateById(commodity);
        if (rs) {
            return Response.success("修改成功");
        } else {
            return Response.bizError("修改失败");
        }
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public Response deleteCommodity(List<Integer> commodityIdList) {
        boolean rs = iCommodeytService.removeByIds(commodityIdList);
        if (rs) {
            return Response.success("删除成功");
        } else {
            return Response.bizError("删除失败");
        }
    }


    @GetMapping
    @ApiOperation(value = "分页获取商品列表")
    public Response getCommodityList(@Valid CommodityPageDto commodity) {
        Commodity com = new Commodity();
        BeanUtils.copyProperties(commodity, com);
        QueryWrapper<Commodity> qw = new QueryWrapper<>(com);
        IPage rs = iCommodeytService.page(new Page<Commodity>(commodity.getCurrent(), commodity.getPageSize()), qw);
        return Response.success("查找完毕", rs);
    }

    @GetMapping("typeList")
    @ApiOperation(value = "获取类型列表")
    public Response getTypeList() {
        return Response.success("查询完毕", iCommodeytService.selectTypeList());
    }
}

