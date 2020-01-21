package com.restaurant.entity.requset;

import com.restaurant.entity.Option;
import com.restaurant.entity.OrderList;
import lombok.Data;

import java.util.List;

@Data
public class OrderListOptionDto extends OrderList {
    List<Option> optionList;
}
