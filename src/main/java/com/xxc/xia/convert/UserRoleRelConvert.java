package com.xxc.xia.convert;

import com.xxc.xia.entity.UserRoleRel;
import com.xxc.xia.dto.userrolerel.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 用户角色关系表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class UserRoleRelConvert {

    public static UserRoleRel convert(UserRoleRelCreateRequest userRoleRelCreateRequest) {
        if (userRoleRelCreateRequest == null) {
            return null;
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setUserId(userRoleRelCreateRequest.getUserId());
        userRoleRel.setRoleKey(userRoleRelCreateRequest.getRoleKey());
        userRoleRel.setExtendInfo(userRoleRelCreateRequest.getExtendInfo());
        userRoleRel.setCreateBy(userRoleRelCreateRequest.getCreateBy());
        userRoleRel.setUpdateBy(userRoleRelCreateRequest.getUpdateBy());
        return userRoleRel;
    }

    public static UserRoleRel convert(UserRoleRelUpdateRequest userRoleRelUpdateRequest) {
        if (userRoleRelUpdateRequest == null) {
            return null;
        }
        UserRoleRel userRoleRel = new UserRoleRel();
        userRoleRel.setUserId(userRoleRelUpdateRequest.getUserId());
        userRoleRel.setRoleKey(userRoleRelUpdateRequest.getRoleKey());
        userRoleRel.setExtendInfo(userRoleRelUpdateRequest.getExtendInfo());
        userRoleRel.setCreateBy(userRoleRelUpdateRequest.getCreateBy());
        userRoleRel.setUpdateBy(userRoleRelUpdateRequest.getUpdateBy());
        return userRoleRel;
    }

    public static UserRoleRelResult convert(UserRoleRel entity) {
        if (entity == null) {
            return null;
        }
        UserRoleRelResult userRoleRelResult = new UserRoleRelResult();
        userRoleRelResult.setId(entity.getId());
        userRoleRelResult.setUserId(entity.getUserId());
        userRoleRelResult.setRoleKey(entity.getRoleKey());
        userRoleRelResult.setExtendInfo(entity.getExtendInfo());
        userRoleRelResult.setCreateBy(entity.getCreateBy());
        userRoleRelResult.setCreateTime(entity.getCreateTime());
        userRoleRelResult.setUpdateBy(entity.getUpdateBy());
        userRoleRelResult.setUpdateTime(entity.getUpdateTime());
        return userRoleRelResult;
    }

    public static List<UserRoleRelResult> convertList(List<UserRoleRel> entityList) {
        if (entityList == null) {
            return null;
        }
        List<UserRoleRelResult> userRoleRelResultList = new ArrayList<>();
        for (UserRoleRel entity : entityList) {
            UserRoleRelResult userRoleRelResult = convert(entity);
            userRoleRelResultList.add(userRoleRelResult);
        }
        return userRoleRelResultList;
    }

    public static PageWrapper<UserRoleRelResult> convertPage(PageWrapper<UserRoleRel> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<UserRoleRelResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}