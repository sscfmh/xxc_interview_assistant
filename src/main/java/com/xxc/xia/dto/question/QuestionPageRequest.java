package com.xxc.xia.dto.question;

import com.xxc.xia.common.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 题目 pageReqVo
 *
 * @author auto gene
 * @create 2025-09-27 12:09:35
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class QuestionPageRequest extends PageRequest {

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    private Long id;

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
     * 题号
     */
    @Schema(description = "题号")
    private Integer questionNo;

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
     * 标签ID 数组
     */
    private List<String> tagIds;

    /**
     * 标签ID 操作类型
     */
    private String tagIdsOpType;

    /**
     * 需要是否已回答标识
     */
    private boolean needAlreadyAnswerFlag;

}