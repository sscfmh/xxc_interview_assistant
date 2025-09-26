package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 参数配置表
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("param_config")
public class ParamConfig extends BaseEntity {

    /**
     * 参数类型
     */
    @TableField("param_type")
    private String paramType;

    /**
     * 参数key
     */
    @TableField("param_key")
    private String paramKey;

    /**
     * 参数value
     */
    @TableField("param_value")
    private String paramValue;

    /**
     * value类型
     */
    @TableField("value_type")
    private String valueType;

    /**
     * 是否公开
     */
    @TableField("pub_flag")
    private String pubFlag;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}