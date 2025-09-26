package com.xxc.xia.config;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.constant.GlobalConstants;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.context.UserLoginInfo;
import com.xxc.xia.common.enums.Logical;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.utils.JWTTokenUtils;
import com.xxc.xia.common.utils.RequestUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/1/12 20:26
 */
@Aspect
@Component
@Slf4j
public class PermissionAspect {

    @Value("${app.check-perm.enabled}")
    private boolean enabled;

    @Pointcut("@annotation(com.xxc.xia.common.annotation.RequirePermissions)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        // 获取注解
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        RequirePermissions requirePermissions = AnnotationUtils.findAnnotation(method,
            RequirePermissions.class);
        HttpServletRequest httpServletRequest = RequestUtils.httpServletRequest();
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = httpServletRequest.getHeader("Authorization");
        }
        UserLoginInfo userLoginInfo = null;
        // 解析登录信息
        if (StringUtils.isNotBlank(token)) {
            userLoginInfo = JWTTokenUtils.verifyToken(token, GlobalConstants.JWT_USER_FIELD,
                GlobalConstants.TOKEN_SECRET, UserLoginInfo.class);
            if (userLoginInfo != null) {
                userLoginInfo.setToken(token);
            }
        }
        if (requirePermissions != null && this.enabled) {
            // 校验没有logout
            AssertUtils.isTrue(StringUtils.isNotBlank(token), "token 已过期或不合法");
            // AssertUtils.isTrue(LoginContext.isValidToken(token), "token 已过期或不合法");
            AssertUtils.notNull(userLoginInfo, "token 已过期或不合法");
            AssertUtils.notEmpty(userLoginInfo.getPermissions(), "没有权限访问该资源");
            boolean hasPerm = false;
            for (String perm : requirePermissions.value()) {
                if (requirePermissions.logical() == Logical.OR) {
                    if (userLoginInfo.getPermissions().contains(perm)) {
                        hasPerm = true;
                        break;
                    }
                } else {
                    hasPerm = true;
                    if (!userLoginInfo.getPermissions().contains(perm)) {
                        hasPerm = false;
                        break;
                    }
                }
            }
            AssertUtils.isTrue(userLoginInfo.isSuperAdmin() || hasPerm, "没有权限访问该资源");
        }

        try {
            // 登录信息放入当前线程
            LoginContext loginContext = new LoginContext();
            loginContext.setUserLoginInfo(userLoginInfo);
            LoginContext.set(loginContext);
            return point.proceed();
        } finally {
            // 登录信息移除
            LoginContext.remove();
        }
    }
}
