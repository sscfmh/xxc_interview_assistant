package com.xxc.xia.dto.answer;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 答案 baseDto
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
public class AnswerBaseDto implements Serializable {

    /**
     * 题目ID
     */
    @Schema(description = "题目ID")
    private String questionId;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extendInfo;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updateBy;

}