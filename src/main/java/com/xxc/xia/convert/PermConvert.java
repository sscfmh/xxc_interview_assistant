package com.xxc.xia.convert;

import com.xxc.xia.entity.Perm;
import com.xxc.xia.dto.perm.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 权限表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class PermConvert {

    public static Perm convert(PermCreateRequest permCreateRequest) {
        if (permCreateRequest == null) {
            return null;
        }
        Perm perm = new Perm();
        perm.setPermKey(permCreateRequest.getPermKey());
        perm.setParentKey(permCreateRequest.getParentKey());
        perm.setPermName(permCreateRequest.getPermName());
        perm.setMenuType(permCreateRequest.getMenuType());
        perm.setUiInfo(permCreateRequest.getUiInfo());
        perm.setExtendInfo(permCreateRequest.getExtendInfo());
        perm.setCreateBy(permCreateRequest.getCreateBy());
        perm.setUpdateBy(permCreateRequest.getUpdateBy());
        return perm;
    }

    public static Perm convert(PermUpdateRequest permUpdateRequest) {
        if (permUpdateRequest == null) {
            return null;
        }
        Perm perm = new Perm();
        perm.setPermKey(permUpdateRequest.getPermKey());
        perm.setParentKey(permUpdateRequest.getParentKey());
        perm.setPermName(permUpdateRequest.getPermName());
        perm.setMenuType(permUpdateRequest.getMenuType());
        perm.setUiInfo(permUpdateRequest.getUiInfo());
        perm.setExtendInfo(permUpdateRequest.getExtendInfo());
        perm.setCreateBy(permUpdateRequest.getCreateBy());
        perm.setUpdateBy(permUpdateRequest.getUpdateBy());
        return perm;
    }

    public static PermResult convert(Perm entity) {
        if (entity == null) {
            return null;
        }
        PermResult permResult = new PermResult();
        permResult.setId(entity.getId());
        permResult.setPermKey(entity.getPermKey());
        permResult.setParentKey(entity.getParentKey());
        permResult.setPermName(entity.getPermName());
        permResult.setMenuType(entity.getMenuType());
        permResult.setUiInfo(entity.getUiInfo());
        permResult.setExtendInfo(entity.getExtendInfo());
        permResult.setCreateBy(entity.getCreateBy());
        permResult.setCreateTime(entity.getCreateTime());
        permResult.setUpdateBy(entity.getUpdateBy());
        permResult.setUpdateTime(entity.getUpdateTime());
        return permResult;
    }

    public static List<PermResult> convertList(List<Perm> entityList) {
        if (entityList == null) {
            return null;
        }
        List<PermResult> permResultList = new ArrayList<>();
        for (Perm entity : entityList) {
            PermResult permResult = convert(entity);
            permResultList.add(permResult);
        }
        return permResultList;
    }

    public static PageWrapper<PermResult> convertPage(PageWrapper<Perm> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<PermResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}