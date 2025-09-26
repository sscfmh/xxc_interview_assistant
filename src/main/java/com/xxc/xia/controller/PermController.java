package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.PermConvert;
import com.xxc.xia.dto.perm.*;
import com.xxc.xia.entity.Perm;
import com.xxc.xia.service.impl.PermServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 权限表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:33:20
 */
@Tag(name = "权限表")
@RestController
@RequestMapping("/perm")
public class PermController {


    @Autowired
    private PermServiceImpl permService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"perm:create"})
    @PostMapping("/createPerm")
    public Result<Long> createPerm(@RequestBody @Validated PermCreateRequest request) {
        Long id = permService.createPerm(request);
        return ResultFactory.success(id);
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"perm:delete"})
    @PostMapping("/deletePermById/{id}")
    public Result<Boolean> deletePermById(@PathVariable("id") Long id) {
        permService.deletePerm(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"perm:update"})
    @PostMapping("/updatePermById")
    public Result<Boolean> updatePermById(@RequestBody @Validated PermUpdateRequest request) {
        permService.updatePerm(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"perm:query"})
    @GetMapping("/queryPermById/{id}")
    public Result<PermResult> queryPermById(@PathVariable("id") Long id) {
        Perm perm = permService.getPerm(id);
        return ResultFactory.success(PermConvert.convert(perm));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"perm:query"})
    @PostMapping("/listQueryPermByIds")
    public Result<List<PermResult>> listQueryPermByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Perm> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = permService.list();
        } else {
            source = permService.getPermList(ids);;
        }
        List<PermResult> data = PermConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"perm:query"})
    @PostMapping("/pageQueryPerm")
    public Result<PageWrapper<PermResult>> pageQueryPerm(@RequestBody @Validated PermPageRequest request) {
        PageWrapper<Perm> pageWrapper = permService.getPermPage(request);
        return ResultFactory.success(PermConvert.convertPage(pageWrapper));
    }
}
