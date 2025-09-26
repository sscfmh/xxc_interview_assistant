package com.xxc.xia.dto.rolepermrel;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 角色权限关系表 updateRequest
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RolePermRelUpdateRequest extends RolePermRelBaseDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;
}