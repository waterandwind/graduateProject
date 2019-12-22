package com.baomidou.ant.commodity.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyw
 * @since 2019-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("commodity")
public class Commodity extends Model<Commodity> {

    private static final long serialVersionUID=1L;

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


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
