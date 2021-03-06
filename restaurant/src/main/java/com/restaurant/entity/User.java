package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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

    private static final long serialVersionUID = 1L;

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

    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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


    /**
     * 账号类型：0 用户；1 后台账号
     */
    @TableField("type")

    private Integer type;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
