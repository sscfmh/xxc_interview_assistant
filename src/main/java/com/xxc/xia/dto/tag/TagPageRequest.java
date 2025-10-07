package com.xxc.xia.dto.tag;

import com.xxc.xia.common.request.PageRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 标签 pageReqVo
 *
 * @author auto gene
 * @create 2025-10-07 17:58:50
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagPageRequest extends PageRequest {

    /**
     * ID
     */
    @Schema(description = "ID")
    private Long id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String tagName;

    /**
     * 介绍
     */
    @Schema(description = "介绍")
    private String tagIntroduce;

    /**
     * tag头图地址
     */
    @Schema(description = "tag头图地址")
    private String tagImgUrl;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extendInfo;

    /**
     * 创建者
     */
    @Schema(description = "创建者")
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
     * 更新者
     */
    @Schema(description = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 更新时间 start
     */
    @Schema(description = "更新时间区间开始")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTimeStart;

    /**
     * 更新时间 end
     */
    @Schema(description = "更新时间区间结束")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTimeEnd;

}