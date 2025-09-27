package com.xxc.xia.convert;

import com.xxc.xia.entity.Role;
import com.xxc.xia.dto.role.*;
import com.xxc.xia.common.wrapper.PageWrapper;

import java.util.*;

/**
 * 角色表 convert
 *
 * @author xxc
 * @create 2025-09-24 01:06:55
 */
public class RoleConvert {

    public static Role convert(RoleCreateRequest roleCreateRequest) {
        if (roleCreateRequest == null) {
            return null;
        }
        Role role = new Role();
        role.setRoleKey(roleCreateRequest.getRoleKey());
        role.setRoleName(roleCreateRequest.getRoleName());
        role.setExtendInfo(roleCreateRequest.getExtendInfo());
        role.setCreateBy(roleCreateRequest.getCreateBy());
        role.setUpdateBy(roleCreateRequest.getUpdateBy());
        return role;
    }

    public static Role convert(RoleUpdateRequest roleUpdateRequest) {
        if (roleUpdateRequest == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleUpdateRequest.getId());
        role.setRoleKey(roleUpdateRequest.getRoleKey());
        role.setRoleName(roleUpdateRequest.getRoleName());
        role.setExtendInfo(roleUpdateRequest.getExtendInfo());
        role.setCreateBy(roleUpdateRequest.getCreateBy());
        role.setUpdateBy(roleUpdateRequest.getUpdateBy());
        return role;
    }

    public static RoleResult convert(Role entity) {
        if (entity == null) {
            return null;
        }
        RoleResult roleResult = new RoleResult();
        roleResult.setId(entity.getId());
        roleResult.setRoleKey(entity.getRoleKey());
        roleResult.setRoleName(entity.getRoleName());
        roleResult.setExtendInfo(entity.getExtendInfo());
        roleResult.setCreateBy(entity.getCreateBy());
        roleResult.setCreateTime(entity.getCreateTime());
        roleResult.setUpdateBy(entity.getUpdateBy());
        roleResult.setUpdateTime(entity.getUpdateTime());
        return roleResult;
    }

    public static List<RoleResult> convertList(List<Role> entityList) {
        if (entityList == null) {
            return null;
        }
        List<RoleResult> roleResultList = new ArrayList<>();
        for (Role entity : entityList) {
            RoleResult roleResult = convert(entity);
            roleResultList.add(roleResult);
        }
        return roleResultList;
    }

    public static PageWrapper<RoleResult> convertPage(PageWrapper<Role> pw) {
        if (pw == null) {
            return null;
        }
        PageWrapper<RoleResult> resultPw = PageWrapper.build(pw.getTotal(), convertList(pw.getData()));
        return resultPw;
    }

}