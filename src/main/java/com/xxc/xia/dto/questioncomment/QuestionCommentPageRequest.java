package com.xxc.xia.dto.questioncomment;

import com.xxc.xia.common.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题评论 pageReqVo
 *
 * @author auto gene
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QuestionCommentPageRequest extends PageRequest {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

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
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 创建时间 start
     */
    @Schema(description = "创建时间区间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTimeStart;

    /**
     * 创建时间 end
     */
    @Schema(description = "创建时间区间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTimeEnd;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private String updateBy;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 修改时间 start
     */
    @Schema(description = "修改时间区间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTimeStart;

    /**
     * 修改时间 end
     */
    @Schema(description = "修改时间区间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTimeEnd;

    /**
     * 是否需要用户信息
     */
    @Schema(description = "是否需要用户信息")
    private boolean needUserInfo;

}