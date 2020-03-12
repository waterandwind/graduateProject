package com.restaurant.entity;

import com.restaurant.entity.requset.Page;
import lombok.Data;

@Data
public class RolePageDto extends Page {
    Integer status;
    String roleName;
}
