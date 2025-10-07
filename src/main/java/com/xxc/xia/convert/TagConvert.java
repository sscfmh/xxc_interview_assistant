package com.xxc.xia.convert;

import com.xxc.xia.entity.Tag;
import com.xxc.xia.dto.tag.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 标签 convert
 *
 * @author xxc
 * @create 2025-10-07 17:58:50
 */
public class TagConvert {

    public static Tag convert(TagCreateRequest tagCreateRequest) {
        if (tagCreateRequest == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setTagName(tagCreateRequest.getTagName());
        tag.setTagIntroduce(tagCreateRequest.getTagIntroduce());
        tag.setTagImgUrl(tagCreateRequest.getTagImgUrl());
        tag.setExtendInfo(tagCreateRequest.getExtendInfo());
        tag.setCreateBy(tagCreateRequest.getCreateBy());
        tag.setUpdateBy(tagCreateRequest.getUpdateBy());
        return tag;
    }

    public static Tag convert(TagUpdateRequest tagUpdateRequest) {
        if (tagUpdateRequest == null) {
            return null;
        }
        Tag tag = new Tag();
        tag.setId(tagUpdateRequest.getId());
        tag.setTagName(tagUpdateRequest.getTagName());
        tag.setTagIntroduce(tagUpdateRequest.getTagIntroduce());
        tag.setTagImgUrl(tagUpdateRequest.getTagImgUrl());
        tag.setExtendInfo(tagUpdateRequest.getExtendInfo());
        tag.setCreateBy(tagUpdateRequest.getCreateBy());
        tag.setUpdateBy(tagUpdateRequest.getUpdateBy());
        return tag;
    }

    public static TagResult convert(Tag entity) {
        if (entity == null) {
            return null;
        }
        TagResult tagResult = new TagResult();
        tagResult.setId(entity.getId());
        tagResult.setTagName(entity.getTagName());
        tagResult.setTagIntroduce(entity.getTagIntroduce());
        tagResult.setTagImgUrl(entity.getTagImgUrl());
        tagResult.setExtendInfo(entity.getExtendInfo());
        tagResult.setCreateBy(entity.getCreateBy());
        tagResult.setCreateTime(entity.getCreateTime());
        tagResult.setUpdateBy(entity.getUpdateBy());
        tagResult.setUpdateTime(entity.getUpdateTime());
        return tagResult;
    }

    public static List<TagResult> convertList(List<Tag> entityList) {
        if (entityList == null) {
            return null;
        }
        List<TagResult> tagResultList = new ArrayList<>();
        for (Tag entity : entityList) {
            TagResult tagResult = convert(entity);
            tagResultList.add(tagResult);
        }
        return tagResultList;
    }

    public static PageWrapper<TagResult> convertPage(PageWrapper<Tag> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<TagResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}