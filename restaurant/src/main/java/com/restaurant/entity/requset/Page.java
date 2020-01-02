package com.restaurant.entity.requset;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
public class Page {
    @NotNull(message = "当前页不能为空")
    private Integer current;
    @NotNull(message = "分页大小不能为空")
    @DecimalMin(value = "1", message = "页面大小最小为1")
    private Integer pageSize;
}
