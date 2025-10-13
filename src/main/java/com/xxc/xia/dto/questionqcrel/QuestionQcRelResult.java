package com.xxc.xia.dto.questionqcrel;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Date;

/**
 * 题目题集关联 result
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QuestionQcRelResult extends QuestionQcRelBaseDto {

    /**
     * 主键ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 标题
     */
    @Schema(description = "title")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "content")
    private String content;

    /**
     * 题目等级
     */
    @Schema(description = "question_level")
    private String questionLevel;

    /**
     * 是否已提交答案
     */
    @Schema(description = "是否已提交答案")
    private boolean alreadyAnswer;
}