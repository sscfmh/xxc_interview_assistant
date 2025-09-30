package com.xxc.xia.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.constant.GlobalConstants;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.context.UserLoginInfo;
import com.xxc.xia.common.enums.RoleEnum;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.utils.JWTTokenUtils;
import com.xxc.xia.convert.UserConvert;
import com.xxc.xia.dto.user.UserCreateRequest;
import com.xxc.xia.dto.user.UserResult;
import com.xxc.xia.dto.useraccount.LoginRequest;
import com.xxc.xia.dto.useraccount.RegisterRequest;
import com.xxc.xia.dto.userrolerel.UserRoleRelCreateRequest;
import com.xxc.xia.entity.RolePermRel;
import com.xxc.xia.entity.User;
import com.xxc.xia.entity.UserRoleRel;
import com.xxc.xia.service.impl.RolePermRelServiceImpl;
import com.xxc.xia.service.impl.UserRoleRelServiceImpl;
import com.xxc.xia.service.impl.UserServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/9/25 21:27
 */
@RestController
@RequestMapping("/api/userAccount")
public class UserAccountController {

    @Autowired
    private UserServiceImpl        userService;

    @Autowired
    private UserRoleRelServiceImpl userRoleRelService;

    @Autowired
    private RolePermRelServiceImpl rolePermRelService;

    @PostMapping("/login")
    public Result<UserLoginInfo> login(@RequestBody @Validated LoginRequest request) {
        User user = userService.getUser(Long.valueOf(request.getUserId()));
        AssertUtils.notNull(user, "用户名或密码不正确");
        AssertUtils.isTrue(user.getPassword().equals(request.getPassword()), "用户名或密码不正确");

        List<String> roles = new LambdaQueryChainWrapper<>(userRoleRelService.getBaseMapper())
            .eq(UserRoleRel::getUserId, String.valueOf(user.getId())).list().stream()
            .map(x -> x.getRoleKey()).distinct().collect(Collectors.toList());
        List<String> permissions = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(roles)) {
            permissions = new LambdaQueryChainWrapper<>(rolePermRelService.getBaseMapper())
                .in(RolePermRel::getRoleKey, roles).list().stream().map(x -> x.getPermKey())
                .distinct().collect(Collectors.toList());
        }

        // 创建token
        UserLoginInfo userLoginInfo = new UserLoginInfo();
        userLoginInfo.setUserId(String.valueOf(user.getId()));
        userLoginInfo.setNickName(user.getNickName());
        userLoginInfo.setAvatar(user.getAvatar());
        userLoginInfo.setSex(user.getSex());
        userLoginInfo.setRoles(roles);
        userLoginInfo.setPermissions(permissions);
        String token = JWTTokenUtils.createToken(userLoginInfo, GlobalConstants.JWT_USER_FIELD,
            new Date(System.currentTimeMillis() + GlobalConstants.TOKEN_EXPIRE_TIME),
            GlobalConstants.TOKEN_SECRET);
        userLoginInfo.setToken(token);
        LoginContext.addToken(userLoginInfo.getUserId(), token);

        return ResultFactory.success(userLoginInfo);
    }

    @RequirePermissions("common:login")
    @PostMapping("/getUserLoginInfo")
    public Result<UserLoginInfo> getUserLoginInfo() {
        UserLoginInfo userLoginInfo = LoginContext.get().getUserLoginInfo();
        return ResultFactory
            .success(JSON.parseObject(JSON.toJSONString(userLoginInfo), UserLoginInfo.class));
    }

    @RequirePermissions("common:login")
    @PostMapping("/logout")
    public Result<Object> logout() {
        UserLoginInfo userLoginInfo = LoginContext.get().getUserLoginInfo();
        if (userLoginInfo != null) {
            LoginContext.removeToken(userLoginInfo.getUserId(), userLoginInfo.getToken());
        }
        return ResultFactory.success(null);
    }

    @PostMapping("/register")
    public Result<UserResult> register(@RequestBody @Validated RegisterRequest request) {
        UserCreateRequest createRequest = new UserCreateRequest();
        createRequest.setPassword(request.getPassword());
        createRequest.setNickName(request.getNickName());
        createRequest.setAvatar(request.getAvatar());
        createRequest.setSex(request.getSex());
        createRequest.setStatus("1");
        createRequest.setEmail(request.getEmail());
        createRequest.setPhoneNumber(request.getPhoneNumber());
        Long userId = userService.createUser(createRequest);
        User user = userService.getUser(userId);
        UserRoleRelCreateRequest userRoleRelCreateRequest = new UserRoleRelCreateRequest();
        userRoleRelCreateRequest.setUserId(String.valueOf(user.getId()));
        userRoleRelCreateRequest.setRoleKey(RoleEnum.NORMAL_USER.getCode());
        userRoleRelService.createUserRoleRel(userRoleRelCreateRequest);
        return ResultFactory.success(UserConvert.convert(user));
    }
}
