package com.xxc.xia.convert;

import com.xxc.xia.entity.User;
import com.xxc.xia.dto.user.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 用户信息表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class UserConvert {

    public static User convert(UserCreateRequest userCreateRequest) {
        if (userCreateRequest == null) {
            return null;
        }
        User user = new User();
        user.setNickName(userCreateRequest.getNickName());
        user.setEmail(userCreateRequest.getEmail());
        user.setPhoneNumber(userCreateRequest.getPhoneNumber());
        user.setPassword(userCreateRequest.getPassword());
        user.setStatus(userCreateRequest.getStatus());
        user.setSex(userCreateRequest.getSex());
        user.setAvatar(userCreateRequest.getAvatar());
        user.setExtendInfo(userCreateRequest.getExtendInfo());
        user.setCreateBy(userCreateRequest.getCreateBy());
        user.setUpdateBy(userCreateRequest.getUpdateBy());
        return user;
    }

    public static User convert(UserUpdateRequest userUpdateRequest) {
        if (userUpdateRequest == null) {
            return null;
        }
        User user = new User();
        user.setNickName(userUpdateRequest.getNickName());
        user.setEmail(userUpdateRequest.getEmail());
        user.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        user.setPassword(userUpdateRequest.getPassword());
        user.setStatus(userUpdateRequest.getStatus());
        user.setSex(userUpdateRequest.getSex());
        user.setAvatar(userUpdateRequest.getAvatar());
        user.setExtendInfo(userUpdateRequest.getExtendInfo());
        user.setCreateBy(userUpdateRequest.getCreateBy());
        user.setUpdateBy(userUpdateRequest.getUpdateBy());
        return user;
    }

    public static UserResult convert(User entity) {
        if (entity == null) {
            return null;
        }
        UserResult userResult = new UserResult();
        userResult.setId(entity.getId());
        userResult.setNickName(entity.getNickName());
        userResult.setEmail(entity.getEmail());
        userResult.setPhoneNumber(entity.getPhoneNumber());
        userResult.setPassword(entity.getPassword());
        userResult.setStatus(entity.getStatus());
        userResult.setSex(entity.getSex());
        userResult.setAvatar(entity.getAvatar());
        userResult.setExtendInfo(entity.getExtendInfo());
        userResult.setCreateBy(entity.getCreateBy());
        userResult.setCreateTime(entity.getCreateTime());
        userResult.setUpdateBy(entity.getUpdateBy());
        userResult.setUpdateTime(entity.getUpdateTime());
        return userResult;
    }

    public static List<UserResult> convertList(List<User> entityList) {
        if (entityList == null) {
            return null;
        }
        List<UserResult> userResultList = new ArrayList<>();
        for (User entity : entityList) {
            UserResult userResult = convert(entity);
            userResultList.add(userResult);
        }
        return userResultList;
    }

    public static PageWrapper<UserResult> convertPage(PageWrapper<User> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<UserResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}