package com.restaurant.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
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
@TableName("commodity")
public class Commodity extends Model<Commodity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名字
     */
    @TableField("commodity_name")
    private String commodityName;

    /**
     * 图片地址
     */
    @TableField("pic_url")
    private String picUrl;

    /**
     * 备注说明
     */
    @TableField("remark")
    private String remark;
    /**
     * 创建时间
     */
    @TableField(value = "create_date", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createDate;
    /**
     * 更新时间
     */
    @TableField(value = "update_date", fill = FieldFill.UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime updateDate;

    /**
     * 创建人
     */
    @TableField("creator")
    private String creator;

    /**
     * 创建人账号
     */
    @TableField("creator_account")
    private String creatorAccount;

    /**
     * 原价
     */
    @TableField("original_cost")
    private BigDecimal originalCost;

    /**
     * 类型
     */
    @TableField("type")
    private String type;


    /**
     * 上架状态；0 下架；1 上架；
     */
    @TableField("state")
    private Integer state;

    /**
     * 售价
     */
    @TableField("sale_cost")
    private BigDecimal saleCost;
    /**
     * 商品编码
     */
    @TableField("commodity_code")
    private String commodityCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
