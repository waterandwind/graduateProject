package com.restaurant.entity.requset;

import lombok.Data;

@Data
public class CommodityPageDto extends Page {
    /**
     * 类型
     */
    private String sType;

    /**
     * 商品名字
     */
    private String commName;


    /**
     * 状态
     */
    private Integer state;
}
