package com.restaurant.entity.result;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.restaurant.entity.OrderList;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetail {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 创建账号
     */
    private String creatorAccount;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 订单位置
     */
    private String orderPosition;

    /**
     * 支付状态：0 待支付；1 已支付
     */
    private String payState;

    List<OrderList> orderList;
}
