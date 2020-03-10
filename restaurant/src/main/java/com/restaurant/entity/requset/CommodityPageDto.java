package com.restaurant.entity.requset;

import lombok.Data;

@Data
public class CommodityPageDto extends Page {
    /**
     * 类型
     */
    private String type;

    /**
     * 商品名字
     */
    private String commName;
}
