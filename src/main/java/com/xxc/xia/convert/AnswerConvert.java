package com.xxc.xia.convert;

import com.xxc.xia.entity.Answer;
import com.xxc.xia.dto.answer.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 答案 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class AnswerConvert {

    public static Answer convert(AnswerCreateRequest answerCreateRequest) {
        if (answerCreateRequest == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setQuestionId(answerCreateRequest.getQuestionId());
        answer.setUserId(answerCreateRequest.getUserId());
        answer.setContent(answerCreateRequest.getContent());
        answer.setExtendInfo(answerCreateRequest.getExtendInfo());
        answer.setCreateBy(answerCreateRequest.getCreateBy());
        answer.setUpdateBy(answerCreateRequest.getUpdateBy());
        return answer;
    }

    public static Answer convert(AnswerUpdateRequest answerUpdateRequest) {
        if (answerUpdateRequest == null) {
            return null;
        }
        Answer answer = new Answer();
        answer.setQuestionId(answerUpdateRequest.getQuestionId());
        answer.setUserId(answerUpdateRequest.getUserId());
        answer.setContent(answerUpdateRequest.getContent());
        answer.setExtendInfo(answerUpdateRequest.getExtendInfo());
        answer.setCreateBy(answerUpdateRequest.getCreateBy());
        answer.setUpdateBy(answerUpdateRequest.getUpdateBy());
        return answer;
    }

    public static AnswerResult convert(Answer entity) {
        if (entity == null) {
            return null;
        }
        AnswerResult answerResult = new AnswerResult();
        answerResult.setId(entity.getId());
        answerResult.setQuestionId(entity.getQuestionId());
        answerResult.setUserId(entity.getUserId());
        answerResult.setContent(entity.getContent());
        answerResult.setExtendInfo(entity.getExtendInfo());
        answerResult.setCreateBy(entity.getCreateBy());
        answerResult.setCreateTime(entity.getCreateTime());
        answerResult.setUpdateBy(entity.getUpdateBy());
        answerResult.setUpdateTime(entity.getUpdateTime());
        return answerResult;
    }

    public static List<AnswerResult> convertList(List<Answer> entityList) {
        if (entityList == null) {
            return null;
        }
        List<AnswerResult> answerResultList = new ArrayList<>();
        for (Answer entity : entityList) {
            AnswerResult answerResult = convert(entity);
            answerResultList.add(answerResult);
        }
        return answerResultList;
    }

    public static PageWrapper<AnswerResult> convertPage(PageWrapper<Answer> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<AnswerResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}