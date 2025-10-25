package com.xxc.xia.mapper;

import com.xxc.xia.entity.QuestionComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * 问题评论 mapper
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Mapper
public interface QuestionCommentMapper extends CustomBaseMapper<QuestionComment> {

    /**
     * 添加点赞数
     *
     * @param id
     * @param heartCntDelta
     * @return
     */
    int addCnt(@Param("id") Long id, @Param("heartCntDelta") Integer heartCntDelta);

    /**
     * 获取最近天有评论的n天的评论数
     *
     * @param n
     * @return
     */
    Map<String, Object> selectCommentCntByDay(@Param("n") int n);
}