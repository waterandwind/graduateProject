package com.restaurant.entity.requset;

import lombok.Data;

import java.util.List;
@Data
public class BatchUpdateCommDto {
    List<Integer> idList;
    Integer flag;
}
