package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyw
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User extends Model<User> {

    private static final long serialVersionUID=1L;

    /**
     * 用户id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户名字
     */
    @TableField("name")
    private String name;

    /**
     * 电话号码
     */
    @TableField("phone")
    private String phone;

    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 账号
     */
    @TableField("account_code")
    private String accountCode;

    /**
     * 密码
     */
    @TableField("password")
    private String password;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
