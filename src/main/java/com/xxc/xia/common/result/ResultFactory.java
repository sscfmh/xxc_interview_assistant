package com.xxc.xia.common.result;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/11 13:56
 */
public class ResultFactory {

    private static final String SUCCESS = "success";

    public static <T> Result<T> success(T data) {
        return success(data, SUCCESS);
    }

    public static <T> Result<T> success(T data, String message) {
        Result result = new Result();
        result.setCode(200);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> error(String message) {
        return error(500, message);
    }

    public static <T> Result<T> error(int code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
