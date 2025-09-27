package com.xxc.xia.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 题集
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
@TableName("question_collection")
public class QuestionCollection extends BaseEntity {

    /**
     * 标题
     */
    @TableField("title")
    private String title;

    /**
     * 摘要
     */
    @TableField("outline")
    private String outline;

    /**
     * 用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 封面图片
     */
    @TableField("img_url")
    private String imgUrl;

    /**
     * 收藏量
     */
    @TableField("fav_cnt")
    private Integer favCnt;

    /**
     * 创建来源
     */
    @TableField("create_source")
    private String createSource;

    /**
     * 标签
     */
    @TableField("tags")
    private String tags;

    /**
     * 扩展信息
     */
    @TableField("extend_info")
    private String extendInfo;

}