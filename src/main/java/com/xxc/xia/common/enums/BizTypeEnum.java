package com.xxc.xia.common.enums;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 19:03
 */
public enum BizTypeEnum {
    FAV_QUESTION("收藏题目"),
    FAV_QC("收藏题集"),
    USER_AQ_PER_DAY("用户每日答题"),
    ;

    private final String desc;

    BizTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * getByName
     */
    public static BizTypeEnum getByName(String name) {
        for (BizTypeEnum value : values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
