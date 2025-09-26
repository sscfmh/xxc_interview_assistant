package com.xxc.xia.convert;

import com.xxc.xia.entity.RolePermRel;
import com.xxc.xia.dto.rolepermrel.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 角色权限关系表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class RolePermRelConvert {

    public static RolePermRel convert(RolePermRelCreateRequest rolePermRelCreateRequest) {
        if (rolePermRelCreateRequest == null) {
            return null;
        }
        RolePermRel rolePermRel = new RolePermRel();
        rolePermRel.setRoleKey(rolePermRelCreateRequest.getRoleKey());
        rolePermRel.setPermKey(rolePermRelCreateRequest.getPermKey());
        rolePermRel.setExtendInfo(rolePermRelCreateRequest.getExtendInfo());
        rolePermRel.setCreateBy(rolePermRelCreateRequest.getCreateBy());
        rolePermRel.setUpdateBy(rolePermRelCreateRequest.getUpdateBy());
        return rolePermRel;
    }

    public static RolePermRel convert(RolePermRelUpdateRequest rolePermRelUpdateRequest) {
        if (rolePermRelUpdateRequest == null) {
            return null;
        }
        RolePermRel rolePermRel = new RolePermRel();
        rolePermRel.setRoleKey(rolePermRelUpdateRequest.getRoleKey());
        rolePermRel.setPermKey(rolePermRelUpdateRequest.getPermKey());
        rolePermRel.setExtendInfo(rolePermRelUpdateRequest.getExtendInfo());
        rolePermRel.setCreateBy(rolePermRelUpdateRequest.getCreateBy());
        rolePermRel.setUpdateBy(rolePermRelUpdateRequest.getUpdateBy());
        return rolePermRel;
    }

    public static RolePermRelResult convert(RolePermRel entity) {
        if (entity == null) {
            return null;
        }
        RolePermRelResult rolePermRelResult = new RolePermRelResult();
        rolePermRelResult.setId(entity.getId());
        rolePermRelResult.setRoleKey(entity.getRoleKey());
        rolePermRelResult.setPermKey(entity.getPermKey());
        rolePermRelResult.setExtendInfo(entity.getExtendInfo());
        rolePermRelResult.setCreateBy(entity.getCreateBy());
        rolePermRelResult.setCreateTime(entity.getCreateTime());
        rolePermRelResult.setUpdateBy(entity.getUpdateBy());
        rolePermRelResult.setUpdateTime(entity.getUpdateTime());
        return rolePermRelResult;
    }

    public static List<RolePermRelResult> convertList(List<RolePermRel> entityList) {
        if (entityList == null) {
            return null;
        }
        List<RolePermRelResult> rolePermRelResultList = new ArrayList<>();
        for (RolePermRel entity : entityList) {
            RolePermRelResult rolePermRelResult = convert(entity);
            rolePermRelResultList.add(rolePermRelResult);
        }
        return rolePermRelResultList;
    }

    public static PageWrapper<RolePermRelResult> convertPage(PageWrapper<RolePermRel> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<RolePermRelResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}