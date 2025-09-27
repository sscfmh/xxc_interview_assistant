package com.xxc.xia.convert;

import com.xxc.xia.entity.QuestionCollection;
import com.xxc.xia.dto.questioncollection.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 题集 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class QuestionCollectionConvert {

    public static QuestionCollection convert(QuestionCollectionCreateRequest questionCollectionCreateRequest) {
        if (questionCollectionCreateRequest == null) {
            return null;
        }
        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.setTitle(questionCollectionCreateRequest.getTitle());
        questionCollection.setOutline(questionCollectionCreateRequest.getOutline());
        questionCollection.setUserId(questionCollectionCreateRequest.getUserId());
        questionCollection.setImgUrl(questionCollectionCreateRequest.getImgUrl());
        questionCollection.setFavCnt(questionCollectionCreateRequest.getFavCnt());
        questionCollection.setCreateSource(questionCollectionCreateRequest.getCreateSource());
        questionCollection.setTags(questionCollectionCreateRequest.getTags());
        questionCollection.setExtendInfo(questionCollectionCreateRequest.getExtendInfo());
        questionCollection.setCreateBy(questionCollectionCreateRequest.getCreateBy());
        questionCollection.setUpdateBy(questionCollectionCreateRequest.getUpdateBy());
        return questionCollection;
    }

    public static QuestionCollection convert(QuestionCollectionUpdateRequest questionCollectionUpdateRequest) {
        if (questionCollectionUpdateRequest == null) {
            return null;
        }
        QuestionCollection questionCollection = new QuestionCollection();
        questionCollection.setTitle(questionCollectionUpdateRequest.getTitle());
        questionCollection.setOutline(questionCollectionUpdateRequest.getOutline());
        questionCollection.setUserId(questionCollectionUpdateRequest.getUserId());
        questionCollection.setImgUrl(questionCollectionUpdateRequest.getImgUrl());
        questionCollection.setFavCnt(questionCollectionUpdateRequest.getFavCnt());
        questionCollection.setCreateSource(questionCollectionUpdateRequest.getCreateSource());
        questionCollection.setTags(questionCollectionUpdateRequest.getTags());
        questionCollection.setExtendInfo(questionCollectionUpdateRequest.getExtendInfo());
        questionCollection.setCreateBy(questionCollectionUpdateRequest.getCreateBy());
        questionCollection.setUpdateBy(questionCollectionUpdateRequest.getUpdateBy());
        return questionCollection;
    }

    public static QuestionCollectionResult convert(QuestionCollection entity) {
        if (entity == null) {
            return null;
        }
        QuestionCollectionResult questionCollectionResult = new QuestionCollectionResult();
        questionCollectionResult.setId(entity.getId());
        questionCollectionResult.setTitle(entity.getTitle());
        questionCollectionResult.setOutline(entity.getOutline());
        questionCollectionResult.setUserId(entity.getUserId());
        questionCollectionResult.setImgUrl(entity.getImgUrl());
        questionCollectionResult.setFavCnt(entity.getFavCnt());
        questionCollectionResult.setCreateSource(entity.getCreateSource());
        questionCollectionResult.setTags(entity.getTags());
        questionCollectionResult.setExtendInfo(entity.getExtendInfo());
        questionCollectionResult.setCreateBy(entity.getCreateBy());
        questionCollectionResult.setCreateTime(entity.getCreateTime());
        questionCollectionResult.setUpdateBy(entity.getUpdateBy());
        questionCollectionResult.setUpdateTime(entity.getUpdateTime());
        return questionCollectionResult;
    }

    public static List<QuestionCollectionResult> convertList(List<QuestionCollection> entityList) {
        if (entityList == null) {
            return null;
        }
        List<QuestionCollectionResult> questionCollectionResultList = new ArrayList<>();
        for (QuestionCollection entity : entityList) {
            QuestionCollectionResult questionCollectionResult = convert(entity);
            questionCollectionResultList.add(questionCollectionResult);
        }
        return questionCollectionResultList;
    }

    public static PageWrapper<QuestionCollectionResult> convertPage(PageWrapper<QuestionCollection> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<QuestionCollectionResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}