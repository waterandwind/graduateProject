package com.restaurant.entity;

import lombok.Data;

import java.util.List;
@Data
public class AccountRole extends User {
    List<Role> roleList;
}
