package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 权限表
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("perm")
public class Perm extends BaseEntity {

    /**
     * 权限key
     */
    @TableField("perm_key")
    private String permKey;

    /**
     * 父权限key
     */
    @TableField("parent_key")
    private String parentKey;

    /**
     * 权限名称
     */
    @TableField("perm_name")
    private String permName;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    @TableField("menu_type")
    private String menuType;

    /**
     * ui信息
     */
    @TableField("ui_info")
    private String uiInfo;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}