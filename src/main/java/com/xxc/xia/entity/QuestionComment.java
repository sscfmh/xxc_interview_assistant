package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 问题评论
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("question_comment")
public class QuestionComment extends BaseEntity {

    /**
     * 题目ID
     */
    @TableField("question_id")
    private String questionId;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论内容
     */
    @TableField("content")
    private String content;

    /**
     * 评论层级
     */
    @TableField("comment_level")
    private String commentLevel;

    /**
     * 一级评论ID
     */
    @TableField("fl_comment_id")
    private Long flCommentId;

    /**
     * 回复ID
     */
    @TableField("reply_id")
    private Long replyId;

    /**
     * 评论状态
     */
    @TableField("comment_status")
    private String commentStatus;

    /**
     * 点赞数量
     */
    @TableField("heart_cnt")
    private Integer heartCnt;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}