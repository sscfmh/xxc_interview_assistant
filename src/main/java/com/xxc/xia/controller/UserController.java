package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.UserConvert;
import com.xxc.xia.dto.user.*;
import com.xxc.xia.entity.User;
import com.xxc.xia.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户信息表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Tag(name = "用户信息表")
@RestController
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"user:create"})
    @PostMapping("/createUser")
    public Result<Long> createUser(@RequestBody @Validated UserCreateRequest request) {
        Long id = userService.createUser(request);
        return ResultFactory.success(id);
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"user:delete"})
    @PostMapping("/deleteUserById/{id}")
    public Result<Boolean> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"user:update"})
    @PostMapping("/updateUserById")
    public Result<Boolean> updateUserById(@RequestBody @Validated UserUpdateRequest request) {
        userService.updateUser(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"user:query"})
    @GetMapping("/queryUserById/{id}")
    public Result<UserResult> queryUserById(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        return ResultFactory.success(UserConvert.convert(user));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"user:query"})
    @PostMapping("/listQueryUserByIds")
    public Result<List<UserResult>> listQueryUserByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<User> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = userService.list();
        } else {
            source = userService.getUserList(ids);;
        }
        List<UserResult> data = UserConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"user:query"})
    @PostMapping("/pageQueryUser")
    public Result<PageWrapper<UserResult>> pageQueryUser(@RequestBody @Validated UserPageRequest request) {
        PageWrapper<User> pageWrapper = userService.getUserPage(request);
        return ResultFactory.success(UserConvert.convertPage(pageWrapper));
    }
}
