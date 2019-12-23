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
@TableName("main_order")
public class MainOrder extends Model<MainOrder> {

    private static final long serialVersionUID=1L;

    /**
     * 订单id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 创建时间
     */
    @TableField("create_date")
    private LocalDateTime createDate;

    /**
     * 总价
     */
    @TableField("total_price")
    private BigDecimal totalPrice;

    /**
     * 创建人
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建账号
     */
    @TableField("creator_account")
    private String creatorAccount;

    /**
     * 订单编码
     */
    @TableField("order_code")
    private String orderCode;

    /**
     * 订单位置
     */
    @TableField("order_position")
    private String orderPosition;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
