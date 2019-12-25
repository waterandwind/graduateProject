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
@TableName("commodity")
public class Commodity extends Model<Commodity> {

    private static final long serialVersionUID=1L;

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

    @TableField("create_date")
    private LocalDateTime createDate;

    @TableField("update_date")
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
    private String  saleCost;

    /**
     * 售价
     */
    @TableField("sale_cost")
    private BigDecimal type;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
