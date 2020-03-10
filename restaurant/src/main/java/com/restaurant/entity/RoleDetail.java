package com.restaurant.entity;

import lombok.Data;

import java.util.List;
@Data
public class RoleDetail extends Role {
    List<Right> rights;
}
