package com.xxc.xia.dto.paramconfig;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 参数配置表 baseDto
 *
 * @author xxc
 * @create 2025-09-26 22:58:12
 */
@Data
public class ParamConfigBaseDto implements Serializable {

    /**
     * 参数类型
     */
    @Schema(description = "参数类型")
    private String paramType;

    /**
     * 参数key
     */
    @Schema(description = "参数key")
    private String paramKey;

    /**
     * 参数value
     */
    @Schema(description = "参数value")
    private String paramValue;

    /**
     * value类型
     */
    @Schema(description = "value类型")
    private String valueType;

    /**
     * 是否公开
     */
    @Schema(description = "是否公开")
    private String pubFlag;

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