package com.restaurant.entity;

import com.restaurant.entity.requset.Page;
import lombok.Data;

@Data
public class OrderListPageDto extends Page {
    Integer isRead;
    String orderCode;
}
