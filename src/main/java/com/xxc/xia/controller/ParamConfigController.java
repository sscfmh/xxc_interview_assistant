package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.ParamConfigConvert;
import com.xxc.xia.dto.paramconfig.*;
import com.xxc.xia.entity.ParamConfig;
import com.xxc.xia.service.impl.ParamConfigServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 参数配置表 controller
 *
 * @author xxc
 * @create 2025-09-26 23:32:53
 */
@Tag(name = "参数配置表")
@RestController
@RequestMapping("/api/paramConfig")
public class ParamConfigController {


    @Autowired
    private ParamConfigServiceImpl paramConfigService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"paramConfig:create"})
    @PostMapping("/createParamConfig")
    public Result<String> createParamConfig(@RequestBody @Validated ParamConfigCreateRequest request) {
        Long id = paramConfigService.createParamConfig(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"paramConfig:delete"})
    @PostMapping("/deleteParamConfigById/{id}")
    public Result<Boolean> deleteParamConfigById(@PathVariable("id") Long id) {
        paramConfigService.deleteParamConfig(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"paramConfig:update"})
    @PostMapping("/updateParamConfigById")
    public Result<Boolean> updateParamConfigById(@RequestBody @Validated ParamConfigUpdateRequest request) {
        paramConfigService.updateParamConfig(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"paramConfig:query"})
    @GetMapping("/queryParamConfigById/{id}")
    public Result<ParamConfigResult> queryParamConfigById(@PathVariable("id") Long id) {
        ParamConfig paramConfig = paramConfigService.getParamConfig(id);
        return ResultFactory.success(ParamConfigConvert.convert(paramConfig));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"paramConfig:query"})
    @PostMapping("/listQueryParamConfigByIds")
    public Result<List<ParamConfigResult>> listQueryParamConfigByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<ParamConfig> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = paramConfigService.list();
        } else {
            source = paramConfigService.getParamConfigList(ids);;
        }
        List<ParamConfigResult> data = ParamConfigConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"paramConfig:query"})
    @PostMapping("/pageQueryParamConfig")
    public Result<PageWrapper<ParamConfigResult>> pageQueryParamConfig(@RequestBody @Validated ParamConfigPageRequest request) {
        PageWrapper<ParamConfig> pageWrapper = paramConfigService.getParamConfigPage(request);
        return ResultFactory.success(ParamConfigConvert.convertPage(pageWrapper));
    }
}
