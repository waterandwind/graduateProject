package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyw
 * @since 2020-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("right_table")
public class Right extends Model<Right> {

    private static final long serialVersionUID=1L;

    @TableField("id")
    private Integer id;

    @TableField("right_name")
    private String rightName;

    @TableField("right_url")
    private String rightUrl;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
