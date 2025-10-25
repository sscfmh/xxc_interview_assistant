package com.xxc.xia.dto.signinrecord;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * 签到记录 baseDto
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Data
public class SignInRecordBaseDto implements Serializable {

    /**
     * 业务类型
     */
    @Schema(description = "业务类型")
    private String bizType;

    /**
     * 业务ID
     */
    @Schema(description = "业务ID")
    private String bizId;

    /**
     * 年月
     */
    @Schema(description = "年月")
    private String ym;

    /**
     * 签到标识
     */
    @Schema(description = "签到标识")
    private Integer mark;

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