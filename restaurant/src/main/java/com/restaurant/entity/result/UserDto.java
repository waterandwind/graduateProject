package com.restaurant.entity.result;

import lombok.Data;


@Data
public class UserDto {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户名字
     */
    private String name;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 账号
     */
    private String accountCode;

    /**
     * token登录凭证
     */
    private String token;

}
