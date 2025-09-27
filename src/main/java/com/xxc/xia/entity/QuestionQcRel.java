package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 题目题集关联
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
@TableName("question_qc_rel")
public class QuestionQcRel extends BaseEntity {

    /**
     * 题目ID
     */
    @TableField("question_id")
    private String questionId;

    /**
     * 题集ID
     */
    @TableField("qc_id")
    private String qcId;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}