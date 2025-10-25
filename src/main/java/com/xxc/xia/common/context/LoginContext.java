package com.xxc.xia.common.context;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 20:21
 */
@Data
public class LoginContext implements Serializable {

    /**
     * 有效token
     */
    private static final Map<String, String>       VALID_TOKEN_MAP         = new ConcurrentHashMap<>();

    private static final ThreadLocal<LoginContext> loginContextThreadLocal = new ThreadLocal<>();

    private UserLoginInfo                          userLoginInfo;

    public LoginContext cloneInstance() {
        LoginContext loginContext = new LoginContext();
        loginContext.setUserLoginInfo(
            JSON.parseObject(JSON.toJSONString(this.userLoginInfo), UserLoginInfo.class));
        return loginContext;
    }

    public static LoginContext get() {
        return loginContextThreadLocal.get();
    }

    public static boolean isLogin() {
        return loginContextThreadLocal.get() != null
               && loginContextThreadLocal.get().getUserLoginInfo() != null
               && loginContextThreadLocal.get().getUserLoginInfo().getUserId() != null;
    }

    public static String getLoginUserId() {
        return Optional.ofNullable(getUserLoginInfo()).map(UserLoginInfo::getUserId).orElse(null);
    }

    public static UserLoginInfo getUserLoginInfo() {
        return Optional.ofNullable(loginContextThreadLocal.get()).map(x -> x.userLoginInfo)
            .orElse(null);
    }

    public static void set(LoginContext loginContext) {
        loginContextThreadLocal.set(loginContext);
    }

    public static void remove() {
        loginContextThreadLocal.remove();
    }

    public static void addToken(String userId, String token) {
        VALID_TOKEN_MAP.putIfAbsent(userId, token);
    }

    public static boolean isValidToken(String token) {
        return VALID_TOKEN_MAP.containsKey(token);
    }

    public static void removeToken(String userId, String token) {
        VALID_TOKEN_MAP.remove(userId, token);
    }

}
