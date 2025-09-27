package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.RolePermRelConvert;
import com.xxc.xia.dto.rolepermrel.*;
import com.xxc.xia.entity.RolePermRel;
import com.xxc.xia.service.impl.RolePermRelServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 角色权限关系表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Tag(name = "角色权限关系表")
@RestController
@RequestMapping("/api/rolePermRel")
public class RolePermRelController {


    @Autowired
    private RolePermRelServiceImpl rolePermRelService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"rolePermRel:create"})
    @PostMapping("/createRolePermRel")
    public Result<Long> createRolePermRel(@RequestBody @Validated RolePermRelCreateRequest request) {
        Long id = rolePermRelService.createRolePermRel(request);
        return ResultFactory.success(id);
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"rolePermRel:delete"})
    @PostMapping("/deleteRolePermRelById/{id}")
    public Result<Boolean> deleteRolePermRelById(@PathVariable("id") Long id) {
        rolePermRelService.deleteRolePermRel(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"rolePermRel:update"})
    @PostMapping("/updateRolePermRelById")
    public Result<Boolean> updateRolePermRelById(@RequestBody @Validated RolePermRelUpdateRequest request) {
        rolePermRelService.updateRolePermRel(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"rolePermRel:query"})
    @GetMapping("/queryRolePermRelById/{id}")
    public Result<RolePermRelResult> queryRolePermRelById(@PathVariable("id") Long id) {
        RolePermRel rolePermRel = rolePermRelService.getRolePermRel(id);
        return ResultFactory.success(RolePermRelConvert.convert(rolePermRel));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"rolePermRel:query"})
    @PostMapping("/listQueryRolePermRelByIds")
    public Result<List<RolePermRelResult>> listQueryRolePermRelByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<RolePermRel> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = rolePermRelService.list();
        } else {
            source = rolePermRelService.getRolePermRelList(ids);;
        }
        List<RolePermRelResult> data = RolePermRelConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"rolePermRel:query"})
    @PostMapping("/pageQueryRolePermRel")
    public Result<PageWrapper<RolePermRelResult>> pageQueryRolePermRel(@RequestBody @Validated RolePermRelPageRequest request) {
        PageWrapper<RolePermRel> pageWrapper = rolePermRelService.getRolePermRelPage(request);
        return ResultFactory.success(RolePermRelConvert.convertPage(pageWrapper));
    }
}
