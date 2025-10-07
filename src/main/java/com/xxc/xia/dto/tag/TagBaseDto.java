package com.xxc.xia.dto.tag;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 标签 baseDto
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
@Data
public class TagBaseDto implements Serializable {

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
     * 更新者
     */
    @Schema(description = "更新者")
    private String updateBy;

}