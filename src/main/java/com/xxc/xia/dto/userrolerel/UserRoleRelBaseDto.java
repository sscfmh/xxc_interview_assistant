package com.xxc.xia.dto.userrolerel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户角色关系表 baseDto
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Data
public class UserRoleRelBaseDto implements Serializable {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;

    /**
     * 角色key
     */
    @Schema(description = "角色key")
    private String roleKey;

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