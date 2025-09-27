package com.xxc.xia.convert;

import com.xxc.xia.entity.QuestionQcRel;
import com.xxc.xia.dto.questionqcrel.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 题目题集关联 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class QuestionQcRelConvert {

    public static QuestionQcRel convert(QuestionQcRelCreateRequest questionQcRelCreateRequest) {
        if (questionQcRelCreateRequest == null) {
            return null;
        }
        QuestionQcRel questionQcRel = new QuestionQcRel();
        questionQcRel.setQuestionId(questionQcRelCreateRequest.getQuestionId());
        questionQcRel.setQcId(questionQcRelCreateRequest.getQcId());
        questionQcRel.setExtendInfo(questionQcRelCreateRequest.getExtendInfo());
        questionQcRel.setCreateBy(questionQcRelCreateRequest.getCreateBy());
        questionQcRel.setUpdateBy(questionQcRelCreateRequest.getUpdateBy());
        return questionQcRel;
    }

    public static QuestionQcRel convert(QuestionQcRelUpdateRequest questionQcRelUpdateRequest) {
        if (questionQcRelUpdateRequest == null) {
            return null;
        }
        QuestionQcRel questionQcRel = new QuestionQcRel();
        questionQcRel.setId(questionQcRelUpdateRequest.getId());
        questionQcRel.setQuestionId(questionQcRelUpdateRequest.getQuestionId());
        questionQcRel.setQcId(questionQcRelUpdateRequest.getQcId());
        questionQcRel.setExtendInfo(questionQcRelUpdateRequest.getExtendInfo());
        questionQcRel.setCreateBy(questionQcRelUpdateRequest.getCreateBy());
        questionQcRel.setUpdateBy(questionQcRelUpdateRequest.getUpdateBy());
        return questionQcRel;
    }

    public static QuestionQcRelResult convert(QuestionQcRel entity) {
        if (entity == null) {
            return null;
        }
        QuestionQcRelResult questionQcRelResult = new QuestionQcRelResult();
        questionQcRelResult.setId(entity.getId());
        questionQcRelResult.setQuestionId(entity.getQuestionId());
        questionQcRelResult.setQcId(entity.getQcId());
        questionQcRelResult.setExtendInfo(entity.getExtendInfo());
        questionQcRelResult.setCreateBy(entity.getCreateBy());
        questionQcRelResult.setCreateTime(entity.getCreateTime());
        questionQcRelResult.setUpdateBy(entity.getUpdateBy());
        questionQcRelResult.setUpdateTime(entity.getUpdateTime());
        return questionQcRelResult;
    }

    public static List<QuestionQcRelResult> convertList(List<QuestionQcRel> entityList) {
        if (entityList == null) {
            return null;
        }
        List<QuestionQcRelResult> questionQcRelResultList = new ArrayList<>();
        for (QuestionQcRel entity : entityList) {
            QuestionQcRelResult questionQcRelResult = convert(entity);
            questionQcRelResultList.add(questionQcRelResult);
        }
        return questionQcRelResultList;
    }

    public static PageWrapper<QuestionQcRelResult> convertPage(PageWrapper<QuestionQcRel> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<QuestionQcRelResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}