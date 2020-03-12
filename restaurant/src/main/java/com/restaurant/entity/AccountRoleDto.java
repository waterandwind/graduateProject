package com.restaurant.entity;

import lombok.Data;

import java.util.List;

@Data
public class AccountRoleDto {
    String accountCode;
    List<Integer> rightList;
}
