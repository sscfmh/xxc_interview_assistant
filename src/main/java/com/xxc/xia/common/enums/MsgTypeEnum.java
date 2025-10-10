package com.xxc.xia.common.enums;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:03
 */
public enum MsgTypeEnum {
    USER_ANSWER_QUESTION("用户解答问题"),
    ;

    private final String desc;

    MsgTypeEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * getByName
     */
    public static MsgTypeEnum getByName(String name) {
        for (MsgTypeEnum value : MsgTypeEnum.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
