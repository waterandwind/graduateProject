package com.restaurant.entity;

import lombok.Data;

import java.util.List;

@Data
public class CommodityWithType {
    List<String> typeList;
    List<Commodity> commodityList;
}
