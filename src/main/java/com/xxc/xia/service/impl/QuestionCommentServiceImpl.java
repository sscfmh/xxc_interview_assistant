package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.convert.QuestionCommentConvert;
import com.xxc.xia.dto.questioncomment.*;
import com.xxc.xia.entity.QuestionComment;
import com.xxc.xia.mapper.QuestionCommentMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 问题评论 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class QuestionCommentServiceImpl extends ServiceImpl<QuestionCommentMapper, QuestionComment> {

    @Resource
    private QuestionCommentMapper questionCommentMapper;

    /**
     * 创建QuestionComment
     *
     * @param request
     * @return
     */
    public Long createQuestionComment(QuestionCommentCreateRequest request) {
        // 插入
        QuestionComment questionComment = QuestionCommentConvert.convert(request);
        questionComment.setCreateTime(new Date());
        questionComment.setUpdateTime(new Date());
        questionCommentMapper.insert(questionComment);
        // 返回
        return questionComment.getId();
    }

    /**
     * 更新QuestionComment
     *
     * @param request
     * @return
     */
    public void updateQuestionComment(QuestionCommentUpdateRequest request) {
        // 校验存在
        checkQuestionCommentExists(request.getId());
        // 更新
        QuestionComment updateObj =  QuestionCommentConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        questionCommentMapper.updateById(updateObj);
    }

    /**
     * 删除QuestionComment
     *
     * @param id
     * @return
     */
    public void deleteQuestionComment(Long id) {
        // 校验存在
        checkQuestionCommentExists(id);
        // 删除
        questionCommentMapper.deleteById(id);
    }

    /**
     * 校验QuestionComment是否存在
     *
     * @param id
     */
    private void checkQuestionCommentExists(Long id) {
        QuestionComment questionComment = questionCommentMapper.selectById(id);
        AssertUtils.notNull(questionComment, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取QuestionComment
     *
     * @param id
     * @return
     */
    public QuestionComment getQuestionComment(Long id) {
        return questionCommentMapper.selectById(id);
    }

    /**
     * 获取QuestionComment列表
     *
     * @param ids
     * @return
     */
    public List<QuestionComment> getQuestionCommentList(Collection<Long> ids) {
        return questionCommentMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取QuestionComment列表
     *
     * @param request
     * @return
     */
    public PageWrapper<QuestionComment> getQuestionCommentPage(QuestionCommentPageRequest request) {
        LambdaQueryWrapper<QuestionComment> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, QuestionComment::getId, request.getId());
        // 题目ID
        lqw.eq(StringUtils.isNotBlank(request.getQuestionId()), QuestionComment::getQuestionId, request.getQuestionId());
        // 用户ID
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), QuestionComment::getUserId, request.getUserId());
        // 评论内容
        lqw.eq(StringUtils.isNotBlank(request.getContent()), QuestionComment::getContent, request.getContent());
        // 评论层级
        lqw.eq(StringUtils.isNotBlank(request.getCommentLevel()), QuestionComment::getCommentLevel, request.getCommentLevel());
        // 一级评论ID
        lqw.eq(request.getFlCommentId() != null, QuestionComment::getFlCommentId, request.getFlCommentId());
        // 回复ID
        lqw.eq(request.getReplyId() != null, QuestionComment::getReplyId, request.getReplyId());
        // 评论状态
        lqw.eq(StringUtils.isNotBlank(request.getCommentStatus()), QuestionComment::getCommentStatus, request.getCommentStatus());
        // 点赞数量
        lqw.eq(request.getHeartCnt() != null, QuestionComment::getHeartCnt, request.getHeartCnt());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), QuestionComment::getExtendInfo, request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), QuestionComment::getCreateBy, request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, QuestionComment::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, QuestionComment::getCreateTime, request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, QuestionComment::getCreateTime, request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), QuestionComment::getUpdateBy, request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, QuestionComment::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, QuestionComment::getUpdateTime, request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, QuestionComment::getUpdateTime, request.getUpdateTimeEnd());
        return questionCommentMapper.selectPage(request, lqw);
    }

}