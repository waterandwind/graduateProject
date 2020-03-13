package com.restaurant.entity.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurant.entity.OrderList;
import com.restaurant.entity.requset.OrderListOptionDto;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDetail implements Serializable {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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
     * 订单读取状态
     */
    private Integer isRead;

    private String remark;

    List<OrderList> commoditys;
}
