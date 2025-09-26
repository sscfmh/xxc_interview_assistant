package com.xxc.xia.common.exception;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/11 14:02
 */
public class BizException extends RuntimeException {
    public BizException(String message) {
        super(message);
    }

    public BizException(String message, Throwable cause) {
        super(message, cause);
    }

    public BizException(Throwable cause) {
        super(cause);
    }
}
