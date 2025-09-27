package com.xxc.xia.convert;

import com.xxc.xia.entity.QuestionComment;
import com.xxc.xia.dto.questioncomment.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 问题评论 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class QuestionCommentConvert {

    public static QuestionComment convert(QuestionCommentCreateRequest questionCommentCreateRequest) {
        if (questionCommentCreateRequest == null) {
            return null;
        }
        QuestionComment questionComment = new QuestionComment();
        questionComment.setQuestionId(questionCommentCreateRequest.getQuestionId());
        questionComment.setUserId(questionCommentCreateRequest.getUserId());
        questionComment.setContent(questionCommentCreateRequest.getContent());
        questionComment.setCommentLevel(questionCommentCreateRequest.getCommentLevel());
        questionComment.setFlCommentId(questionCommentCreateRequest.getFlCommentId());
        questionComment.setReplyId(questionCommentCreateRequest.getReplyId());
        questionComment.setCommentStatus(questionCommentCreateRequest.getCommentStatus());
        questionComment.setHeartCnt(questionCommentCreateRequest.getHeartCnt());
        questionComment.setExtendInfo(questionCommentCreateRequest.getExtendInfo());
        questionComment.setCreateBy(questionCommentCreateRequest.getCreateBy());
        questionComment.setUpdateBy(questionCommentCreateRequest.getUpdateBy());
        return questionComment;
    }

    public static QuestionComment convert(QuestionCommentUpdateRequest questionCommentUpdateRequest) {
        if (questionCommentUpdateRequest == null) {
            return null;
        }
        QuestionComment questionComment = new QuestionComment();
        questionComment.setQuestionId(questionCommentUpdateRequest.getQuestionId());
        questionComment.setUserId(questionCommentUpdateRequest.getUserId());
        questionComment.setContent(questionCommentUpdateRequest.getContent());
        questionComment.setCommentLevel(questionCommentUpdateRequest.getCommentLevel());
        questionComment.setFlCommentId(questionCommentUpdateRequest.getFlCommentId());
        questionComment.setReplyId(questionCommentUpdateRequest.getReplyId());
        questionComment.setCommentStatus(questionCommentUpdateRequest.getCommentStatus());
        questionComment.setHeartCnt(questionCommentUpdateRequest.getHeartCnt());
        questionComment.setExtendInfo(questionCommentUpdateRequest.getExtendInfo());
        questionComment.setCreateBy(questionCommentUpdateRequest.getCreateBy());
        questionComment.setUpdateBy(questionCommentUpdateRequest.getUpdateBy());
        return questionComment;
    }

    public static QuestionCommentResult convert(QuestionComment entity) {
        if (entity == null) {
            return null;
        }
        QuestionCommentResult questionCommentResult = new QuestionCommentResult();
        questionCommentResult.setId(entity.getId());
        questionCommentResult.setQuestionId(entity.getQuestionId());
        questionCommentResult.setUserId(entity.getUserId());
        questionCommentResult.setContent(entity.getContent());
        questionCommentResult.setCommentLevel(entity.getCommentLevel());
        questionCommentResult.setFlCommentId(entity.getFlCommentId());
        questionCommentResult.setReplyId(entity.getReplyId());
        questionCommentResult.setCommentStatus(entity.getCommentStatus());
        questionCommentResult.setHeartCnt(entity.getHeartCnt());
        questionCommentResult.setExtendInfo(entity.getExtendInfo());
        questionCommentResult.setCreateBy(entity.getCreateBy());
        questionCommentResult.setCreateTime(entity.getCreateTime());
        questionCommentResult.setUpdateBy(entity.getUpdateBy());
        questionCommentResult.setUpdateTime(entity.getUpdateTime());
        return questionCommentResult;
    }

    public static List<QuestionCommentResult> convertList(List<QuestionComment> entityList) {
        if (entityList == null) {
            return null;
        }
        List<QuestionCommentResult> questionCommentResultList = new ArrayList<>();
        for (QuestionComment entity : entityList) {
            QuestionCommentResult questionCommentResult = convert(entity);
            questionCommentResultList.add(questionCommentResult);
        }
        return questionCommentResultList;
    }

    public static PageWrapper<QuestionCommentResult> convertPage(PageWrapper<QuestionComment> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<QuestionCommentResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}