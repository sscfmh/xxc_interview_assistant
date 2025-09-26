package com.xxc.xia.common.constant;

/**
 * Desc...
 *
 * @Author xxc
 * @Date 2022/10/19 22:13
 */
public class GlobalConstants {
    /**
     * 应用名
     */
    public static final String APP_NAME           = "app-default-name";
    /**
     * 空值
     */
    public static final String NULL_VALUE         = "null";

    /**
     * 手机号正则表达式
     */
    public static final String PHONE_NUMBER_REGEX = "^1[3-9]\\d{9}$";

    /**
     * 下划线
     */
    public static final String UNDERLINE          = "_";

    /**
     * 连字符
     */
    public static final String HYPHEN             = "-";

    /**
     * JWT TOKEN用户信息字段
     */
    public static final String JWT_USER_FIELD     = "userInfo";

    /**
     * JWT TOKEN过期时间
     */
    public static final long   TOKEN_EXPIRE_TIME  = 1000 * 60 * 60 * 24L;

    /**
     * JWT TOKEN密钥
     */
    public static final String TOKEN_SECRET       = "app-default-secret";
}
