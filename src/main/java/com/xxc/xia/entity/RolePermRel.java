package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 角色权限关系表
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
@TableName("role_perm_rel")
public class RolePermRel extends BaseEntity {

    /**
     * 角色key
     */
    @TableField("role_key")
    private String roleKey;

    /**
     * 权限key
     */
    @TableField("perm_key")
    private String permKey;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}