package com.restaurant.entity;

import lombok.Data;

import java.util.List;

@Data
public class BatchStateDto {
    List<Integer> idList;
    Integer flag;
}
