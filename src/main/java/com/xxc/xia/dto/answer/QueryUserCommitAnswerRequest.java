package com.xxc.xia.dto.answer;

import com.xxc.xia.common.request.BaseRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/23 21:41
 */
@Data
public class QueryUserCommitAnswerRequest extends BaseRequest {
    private String userId;

    @NotBlank
    private String questionId;
}
