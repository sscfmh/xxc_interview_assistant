package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 标签
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("tag")
public class Tag extends BaseEntity {

    /**
     * 名称
     */
    @TableField("tag_name")
    private String tagName;

    /**
     * 介绍
     */
    @TableField("tag_introduce")
    private String tagIntroduce;

    /**
     * tag头图地址
     */
    @TableField("tag_img_url")
    private String tagImgUrl;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}