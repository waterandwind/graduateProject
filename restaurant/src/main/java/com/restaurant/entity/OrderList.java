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
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_list")
public class OrderList extends Model<OrderList> {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品id
     */
    @TableField("commodity_id")
    private Integer commodityId;

    @TableField("commodity_name")
    private String commodityName;

    /**
     * 数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 单价
     */
    @TableField("unit_price")
    private BigDecimal unitPrice;

    /**
     * 总价
     */
    @TableField("total_price")
    private BigDecimal totalPrice;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
