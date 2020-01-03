package com.restaurant.entity.requset;

import com.restaurant.entity.Commodity;
import com.restaurant.entity.Option;
import lombok.Data;

import java.util.List;

@Data
public class CommodityCreateDto extends Commodity {
    //备选列表
    private List<Option> options;
}
