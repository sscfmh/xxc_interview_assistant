package com.xxc.xia.dto.questioncollection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 题集 updateRequest
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QuestionCollectionUpdateRequest extends QuestionCollectionBaseDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;
}