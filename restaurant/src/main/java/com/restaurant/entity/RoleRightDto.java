package com.restaurant.entity;

import lombok.Data;

import java.util.List;

@Data
public class RoleRightDto {
    String roleName;
    Integer roleId;
    List<Integer> rightList;
}
