package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.RoleConvert;
import com.xxc.xia.dto.role.*;
import com.xxc.xia.entity.Role;
import com.xxc.xia.service.impl.RoleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 角色表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Tag(name = "角色表")
@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private RoleServiceImpl roleService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"role:create"})
    @PostMapping("/createRole")
    public Result<Long> createRole(@RequestBody @Validated RoleCreateRequest request) {
        Long id = roleService.createRole(request);
        return ResultFactory.success(id);
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"role:delete"})
    @PostMapping("/deleteRoleById/{id}")
    public Result<Boolean> deleteRoleById(@PathVariable("id") Long id) {
        roleService.deleteRole(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"role:update"})
    @PostMapping("/updateRoleById")
    public Result<Boolean> updateRoleById(@RequestBody @Validated RoleUpdateRequest request) {
        roleService.updateRole(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"role:query"})
    @GetMapping("/queryRoleById/{id}")
    public Result<RoleResult> queryRoleById(@PathVariable("id") Long id) {
        Role role = roleService.getRole(id);
        return ResultFactory.success(RoleConvert.convert(role));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"role:query"})
    @PostMapping("/listQueryRoleByIds")
    public Result<List<RoleResult>> listQueryRoleByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Role> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = roleService.list();
        } else {
            source = roleService.getRoleList(ids);;
        }
        List<RoleResult> data = RoleConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"role:query"})
    @PostMapping("/pageQueryRole")
    public Result<PageWrapper<RoleResult>> pageQueryRole(@RequestBody @Validated RolePageRequest request) {
        PageWrapper<Role> pageWrapper = roleService.getRolePage(request);
        return ResultFactory.success(RoleConvert.convertPage(pageWrapper));
    }
}
