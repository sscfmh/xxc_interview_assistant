package com.xxc.xia.dto.answer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 答案 updateRequest
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnswerUpdateRequest extends AnswerBaseDto {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "是否需要发送答题消息")
    private boolean needSendAnswerQuestionMsg;
}