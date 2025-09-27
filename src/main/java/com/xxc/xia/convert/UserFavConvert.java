package com.xxc.xia.convert;

import com.xxc.xia.entity.UserFav;
import com.xxc.xia.dto.userfav.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 用户收藏 convert
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
public class UserFavConvert {

    public static UserFav convert(UserFavCreateRequest userFavCreateRequest) {
        if (userFavCreateRequest == null) {
            return null;
        }
        UserFav userFav = new UserFav();
        userFav.setBizType(userFavCreateRequest.getBizType());
        userFav.setBizId(userFavCreateRequest.getBizId());
        userFav.setUserId(userFavCreateRequest.getUserId());
        userFav.setExtendInfo(userFavCreateRequest.getExtendInfo());
        userFav.setCreateBy(userFavCreateRequest.getCreateBy());
        userFav.setUpdateBy(userFavCreateRequest.getUpdateBy());
        return userFav;
    }

    public static UserFav convert(UserFavUpdateRequest userFavUpdateRequest) {
        if (userFavUpdateRequest == null) {
            return null;
        }
        UserFav userFav = new UserFav();
        userFav.setId(userFavUpdateRequest.getId());
        userFav.setBizType(userFavUpdateRequest.getBizType());
        userFav.setBizId(userFavUpdateRequest.getBizId());
        userFav.setUserId(userFavUpdateRequest.getUserId());
        userFav.setExtendInfo(userFavUpdateRequest.getExtendInfo());
        userFav.setCreateBy(userFavUpdateRequest.getCreateBy());
        userFav.setUpdateBy(userFavUpdateRequest.getUpdateBy());
        return userFav;
    }

    public static UserFavResult convert(UserFav entity) {
        if (entity == null) {
            return null;
        }
        UserFavResult userFavResult = new UserFavResult();
        userFavResult.setId(entity.getId());
        userFavResult.setBizType(entity.getBizType());
        userFavResult.setBizId(entity.getBizId());
        userFavResult.setUserId(entity.getUserId());
        userFavResult.setExtendInfo(entity.getExtendInfo());
        userFavResult.setCreateBy(entity.getCreateBy());
        userFavResult.setCreateTime(entity.getCreateTime());
        userFavResult.setUpdateBy(entity.getUpdateBy());
        userFavResult.setUpdateTime(entity.getUpdateTime());
        return userFavResult;
    }

    public static List<UserFavResult> convertList(List<UserFav> entityList) {
        if (entityList == null) {
            return null;
        }
        List<UserFavResult> userFavResultList = new ArrayList<>();
        for (UserFav entity : entityList) {
            UserFavResult userFavResult = convert(entity);
            userFavResultList.add(userFavResult);
        }
        return userFavResultList;
    }

    public static PageWrapper<UserFavResult> convertPage(PageWrapper<UserFav> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<UserFavResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}