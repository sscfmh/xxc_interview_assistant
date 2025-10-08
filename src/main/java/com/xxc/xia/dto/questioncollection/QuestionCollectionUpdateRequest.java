package com.xxc.xia.dto.questioncollection;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

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

    /**
     * 删除的题目ID
     */
    private List<String> deleteQuestionIds;

    /**
     * 新增的题目ID
     */
    private List<String> addQuestionIds;
}