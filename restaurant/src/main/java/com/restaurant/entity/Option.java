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
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyw
 * @since 2020-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("commodity_option")
public class Option extends Model<Option> {

    private static final long serialVersionUID=1L;

    /**
     * 商品编码
     */
    @TableField("commodity_code")
    private String commodityCode;

    /**
     * 价格
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 备选名字
     */
    @TableField("option_name")
    private String optionName;

    /**
     * 备选id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;



    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
