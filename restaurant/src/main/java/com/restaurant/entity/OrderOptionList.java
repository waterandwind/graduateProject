package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2020-01-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_option_list")
public class OrderOptionList extends Model<OrderOptionList> {

    private static final long serialVersionUID=1L;

    /**
     * 订单编码
     */
    @TableField("main_order_code")
    private String mainOrderCode;

    /**
     * 商品列表id
     */
    @TableField("order_list_id")
    private Integer orderListId;

    /**
     * 备选名字
     */
    @TableField("option_name")
    private String optionName;

    /**
     * 备选价格
     */
    @TableField("option_price")
    private BigDecimal optionPrice;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
