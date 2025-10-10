package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 题目
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
@TableName("question")
public class Question extends BaseEntity {

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 内容
     */
    @TableField("content")
    private String content;

    /**
     * 参考答案
     */
    @TableField("ref_answer")
    private String refAnswer;

    /**
     * 创建来源
     */
    @TableField("create_source")
    private String createSource;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 题目等级
     */
    @TableField("question_level")
    private String questionLevel;

    /**
     * 标签
     */
    @TableField("tags")
    private String tags;

    /**
     * 题号
     */
    @TableField("question_no")
    private Integer questionNo;

    /**
     * 访问量
     */
    @TableField("view_cnt")
    private Integer viewCnt;

    /**
     * 提交答案量
     */
    @TableField("commit_answer_cnt")
    private Integer commitAnswerCnt;

    /**
     * 收藏量
     */
    @TableField("fav_cnt")
    private Integer favCnt;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}