package com.xxc.xia.common.enums;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/18 12:26
 */
public enum RoleEnum {
    SUPER_ADMIN("SUPER_ADMIN", "超级管理员"),
    BOOK_ADMIN("BOOK_ADMIN", "图书管理员"),
    NORMAL_USER("NORMAL_USER", "普通用户"),
    ;

    private final String code;
    private final String desc;

    RoleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static RoleEnum getByCode(String code) {
        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roleEnum.getCode().equals(code)) {
                return roleEnum;
            }
        }
        return null;
    }
}
