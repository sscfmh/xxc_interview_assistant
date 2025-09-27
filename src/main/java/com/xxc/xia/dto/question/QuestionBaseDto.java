package com.xxc.xia.dto.question;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 题目 baseDto
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
public class QuestionBaseDto implements Serializable {

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 内容
     */
    @Schema(description = "内容")
    private String content;

    /**
     * 参考答案
     */
    @Schema(description = "参考答案")
    private String refAnswer;

    /**
     * 创建来源
     */
    @Schema(description = "创建来源")
    private String createSource;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 题目等级
     */
    @Schema(description = "题目等级")
    private String questionLevel;

    /**
     * 标签
     */
    @Schema(description = "标签")
    private String tags;

    /**
     * 访问量
     */
    @Schema(description = "访问量")
    private Integer viewCnt;

    /**
     * 提交答案量
     */
    @Schema(description = "提交答案量")
    private Integer commitAnswerCnt;

    /**
     * 收藏量
     */
    @Schema(description = "收藏量")
    private Integer favCnt;

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