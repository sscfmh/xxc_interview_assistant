package com.xxc.xia.common.utils;

import com.xxc.xia.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/11 20:51
 */
public class AssertUtils {

    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new BizException(message);
        }
    }

    public static void isFalse(boolean expression, String message) {
        if (expression) {
            throw new BizException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new BizException(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new BizException(message);
        }
    }

    public static void notBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BizException(message);
        }
    }

    public static void isEmpty(Collection<?> collection, String message) {
        if (collection != null && !collection.isEmpty()) {
            throw new BizException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (collection == null || collection.isEmpty()) {
            throw new BizException(message);
        }
    }
}
