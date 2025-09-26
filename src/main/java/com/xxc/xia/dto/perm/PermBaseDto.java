package com.xxc.xia.dto.perm;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 权限表 baseDto
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Data
public class PermBaseDto implements Serializable {

    /**
     * 权限key
     */
    @Schema(description = "权限key")
    private String permKey;

    /**
     * 父权限key
     */
    @Schema(description = "父权限key")
    private String parentKey;

    /**
     * 权限名称
     */
    @Schema(description = "权限名称")
    private String permName;

    /**
     * 菜单类型（D目录 M菜单 B按钮）
     */
    @Schema(description = "菜单类型（D目录 M菜单 B按钮）")
    private String menuType;

    /**
     * ui信息
     */
    @Schema(description = "ui信息")
    private String uiInfo;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extendInfo;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
    private String createBy;

    /**
     * 更新者
     */
    @Schema(description = "更新者")
    private String updateBy;

}