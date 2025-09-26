package com.xxc.xia.common.context;

import com.xxc.xia.common.enums.RoleEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 20:35
 */
@Data
public class UserLoginInfo implements Serializable {

    /**
     * token
     */
    private String       token;

    /**
     * 用户id
     */
    private String       userId;

    /**
     * 用户昵称
     */
    private String       nickName;

    /**
     * 手机号码
     */
    private String       phoneNumber;

    /**
     * 邮箱
     */
    private String       email;

    /**
     * 头像
     */
    private String       avatar;

    /**
     * 性别
     */
    private String       sex;

    /**
     * 扩展信息
     */
    private String       extendInfo;

    /**
     * 角色
     */
    private List<String> roles;

    /**
     * 权限
     */
    private List<String> permissions;

    /**
     * 是否是超级管理员
     * @return
     */
    public boolean isSuperAdmin() {
        return Optional.ofNullable(this.roles).map(x -> x.contains(RoleEnum.SUPER_ADMIN.getCode()))
            .orElse(false);
    }
}
