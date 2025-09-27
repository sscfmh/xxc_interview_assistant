package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.UserRoleRelConvert;
import com.xxc.xia.dto.userrolerel.*;
import com.xxc.xia.entity.UserRoleRel;
import com.xxc.xia.service.impl.UserRoleRelServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户角色关系表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Tag(name = "用户角色关系表")
@RestController
@RequestMapping("/api/userRoleRel")
public class UserRoleRelController {


    @Autowired
    private UserRoleRelServiceImpl userRoleRelService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"userRoleRel:create"})
    @PostMapping("/createUserRoleRel")
    public Result<Long> createUserRoleRel(@RequestBody @Validated UserRoleRelCreateRequest request) {
        Long id = userRoleRelService.createUserRoleRel(request);
        return ResultFactory.success(id);
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"userRoleRel:delete"})
    @PostMapping("/deleteUserRoleRelById/{id}")
    public Result<Boolean> deleteUserRoleRelById(@PathVariable("id") Long id) {
        userRoleRelService.deleteUserRoleRel(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"userRoleRel:update"})
    @PostMapping("/updateUserRoleRelById")
    public Result<Boolean> updateUserRoleRelById(@RequestBody @Validated UserRoleRelUpdateRequest request) {
        userRoleRelService.updateUserRoleRel(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"userRoleRel:query"})
    @GetMapping("/queryUserRoleRelById/{id}")
    public Result<UserRoleRelResult> queryUserRoleRelById(@PathVariable("id") Long id) {
        UserRoleRel userRoleRel = userRoleRelService.getUserRoleRel(id);
        return ResultFactory.success(UserRoleRelConvert.convert(userRoleRel));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"userRoleRel:query"})
    @PostMapping("/listQueryUserRoleRelByIds")
    public Result<List<UserRoleRelResult>> listQueryUserRoleRelByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<UserRoleRel> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = userRoleRelService.list();
        } else {
            source = userRoleRelService.getUserRoleRelList(ids);;
        }
        List<UserRoleRelResult> data = UserRoleRelConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"userRoleRel:query"})
    @PostMapping("/pageQueryUserRoleRel")
    public Result<PageWrapper<UserRoleRelResult>> pageQueryUserRoleRel(@RequestBody @Validated UserRoleRelPageRequest request) {
        PageWrapper<UserRoleRel> pageWrapper = userRoleRelService.getUserRoleRelPage(request);
        return ResultFactory.success(UserRoleRelConvert.convertPage(pageWrapper));
    }
}
