package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 答案
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
@TableName("answer")
public class Answer extends BaseEntity {

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
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}