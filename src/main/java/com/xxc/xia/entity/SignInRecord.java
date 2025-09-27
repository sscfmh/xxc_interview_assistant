package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 签到记录
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
@TableName("sign_in_record")
public class SignInRecord extends BaseEntity {

    /**
     * 业务类型
     */
    @TableField("biz_type")
    private String bizType;

    /**
     * 业务ID
     */
    @TableField("biz_id")
    private String bizId;

    /**
     * 签到标识
     */
    @TableField("year_month")
    private Integer yearMonth;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}