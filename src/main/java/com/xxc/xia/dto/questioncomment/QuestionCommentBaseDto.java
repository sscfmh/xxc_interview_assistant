package com.xxc.xia.dto.questioncomment;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题评论 baseDto
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
public class QuestionCommentBaseDto implements Serializable {

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
     * 评论内容
     */
    @Schema(description = "评论内容")
    private String content;

    /**
     * 评论层级
     */
    @Schema(description = "评论层级")
    private String commentLevel;

    /**
     * 一级评论ID
     */
    @Schema(description = "一级评论ID")
    private Long flCommentId;

    /**
     * 回复ID
     */
    @Schema(description = "回复ID")
    private Long replyId;

    /**
     * 评论状态
     */
    @Schema(description = "评论状态")
    private String commentStatus;

    /**
     * 点赞数量
     */
    @Schema(description = "点赞数量")
    private Integer heartCnt;

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