package com.xxc.xia.dto.answer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 答案 createRequest
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AnswerCreateRequest extends AnswerBaseDto {

    @Schema(description = "是否需要发送答题消息")
    private boolean needSendAnswerQuestionMsg;
}