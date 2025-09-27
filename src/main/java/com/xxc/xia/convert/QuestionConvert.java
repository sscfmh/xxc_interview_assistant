package com.xxc.xia.convert;

import com.xxc.xia.entity.Question;
import com.xxc.xia.dto.question.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 题目 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class QuestionConvert {

    public static Question convert(QuestionCreateRequest questionCreateRequest) {
        if (questionCreateRequest == null) {
            return null;
        }
        Question question = new Question();
        question.setTitle(questionCreateRequest.getTitle());
        question.setContent(questionCreateRequest.getContent());
        question.setRefAnswer(questionCreateRequest.getRefAnswer());
        question.setCreateSource(questionCreateRequest.getCreateSource());
        question.setUserId(questionCreateRequest.getUserId());
        question.setQuestionLevel(questionCreateRequest.getQuestionLevel());
        question.setTags(questionCreateRequest.getTags());
        question.setViewCnt(questionCreateRequest.getViewCnt());
        question.setCommitAnswerCnt(questionCreateRequest.getCommitAnswerCnt());
        question.setFavCnt(questionCreateRequest.getFavCnt());
        question.setExtendInfo(questionCreateRequest.getExtendInfo());
        question.setCreateBy(questionCreateRequest.getCreateBy());
        question.setUpdateBy(questionCreateRequest.getUpdateBy());
        return question;
    }

    public static Question convert(QuestionUpdateRequest questionUpdateRequest) {
        if (questionUpdateRequest == null) {
            return null;
        }
        Question question = new Question();
        question.setId(questionUpdateRequest.getId());
        question.setTitle(questionUpdateRequest.getTitle());
        question.setContent(questionUpdateRequest.getContent());
        question.setRefAnswer(questionUpdateRequest.getRefAnswer());
        question.setCreateSource(questionUpdateRequest.getCreateSource());
        question.setUserId(questionUpdateRequest.getUserId());
        question.setQuestionLevel(questionUpdateRequest.getQuestionLevel());
        question.setTags(questionUpdateRequest.getTags());
        question.setViewCnt(questionUpdateRequest.getViewCnt());
        question.setCommitAnswerCnt(questionUpdateRequest.getCommitAnswerCnt());
        question.setFavCnt(questionUpdateRequest.getFavCnt());
        question.setExtendInfo(questionUpdateRequest.getExtendInfo());
        question.setCreateBy(questionUpdateRequest.getCreateBy());
        question.setUpdateBy(questionUpdateRequest.getUpdateBy());
        return question;
    }

    public static QuestionResult convert(Question entity) {
        if (entity == null) {
            return null;
        }
        QuestionResult questionResult = new QuestionResult();
        questionResult.setId(entity.getId());
        questionResult.setTitle(entity.getTitle());
        questionResult.setContent(entity.getContent());
        questionResult.setRefAnswer(entity.getRefAnswer());
        questionResult.setCreateSource(entity.getCreateSource());
        questionResult.setUserId(entity.getUserId());
        questionResult.setQuestionLevel(entity.getQuestionLevel());
        questionResult.setTags(entity.getTags());
        questionResult.setViewCnt(entity.getViewCnt());
        questionResult.setCommitAnswerCnt(entity.getCommitAnswerCnt());
        questionResult.setFavCnt(entity.getFavCnt());
        questionResult.setExtendInfo(entity.getExtendInfo());
        questionResult.setCreateBy(entity.getCreateBy());
        questionResult.setCreateTime(entity.getCreateTime());
        questionResult.setUpdateBy(entity.getUpdateBy());
        questionResult.setUpdateTime(entity.getUpdateTime());
        return questionResult;
    }

    public static List<QuestionResult> convertList(List<Question> entityList) {
        if (entityList == null) {
            return null;
        }
        List<QuestionResult> questionResultList = new ArrayList<>();
        for (Question entity : entityList) {
            QuestionResult questionResult = convert(entity);
            questionResultList.add(questionResult);
        }
        return questionResultList;
    }

    public static PageWrapper<QuestionResult> convertPage(PageWrapper<Question> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<QuestionResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}