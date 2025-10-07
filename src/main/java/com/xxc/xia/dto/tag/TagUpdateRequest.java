package com.xxc.xia.dto.tag;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 标签 updateRequest
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagUpdateRequest extends TagBaseDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;
}