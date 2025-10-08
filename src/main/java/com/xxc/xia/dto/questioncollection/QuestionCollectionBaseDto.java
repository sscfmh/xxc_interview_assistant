package com.xxc.xia.dto.questioncollection;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 题集 baseDto
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
public class QuestionCollectionBaseDto implements Serializable {

    /**
     * 标题
     */
    @Schema(description = "标题")
    private String title;

    /**
     * 摘要
     */
    @Schema(description = "摘要")
    private String outline;

    /**
     * 用户ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 封面图片
     */
    @Schema(description = "封面图片")
    private String imgUrl;

    /**
     * 收藏量
     */
    @Schema(description = "收藏量")
    private Integer favCnt;

    /**
     * 创建来源
     */
    @Schema(description = "创建来源")
    private String createSource;

    /**
     * 标签
     */
    @Schema(description = "标签")
    private String tags;

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