package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.UserFavConvert;
import com.xxc.xia.dto.userfav.*;
import com.xxc.xia.entity.UserFav;
import com.xxc.xia.service.impl.UserFavServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 用户收藏 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "用户收藏")
@RestController
@RequestMapping("/api/userFav")
public class UserFavController {


    @Autowired
    private UserFavServiceImpl userFavService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"userFav:create"})
    @PostMapping("/createUserFav")
    public Result<String> createUserFav(@RequestBody @Validated UserFavCreateRequest request) {
        Long id = userFavService.createUserFav(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"userFav:delete"})
    @PostMapping("/deleteUserFavById/{id}")
    public Result<Boolean> deleteUserFavById(@PathVariable("id") Long id) {
        userFavService.deleteUserFav(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"userFav:update"})
    @PostMapping("/updateUserFavById")
    public Result<Boolean> updateUserFavById(@RequestBody @Validated UserFavUpdateRequest request) {
        userFavService.updateUserFav(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"userFav:query"})
    @GetMapping("/queryUserFavById/{id}")
    public Result<UserFavResult> queryUserFavById(@PathVariable("id") Long id) {
        UserFav userFav = userFavService.getUserFav(id);
        return ResultFactory.success(UserFavConvert.convert(userFav));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"userFav:query"})
    @PostMapping("/listQueryUserFavByIds")
    public Result<List<UserFavResult>> listQueryUserFavByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<UserFav> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = userFavService.list();
        } else {
            source = userFavService.getUserFavList(ids);;
        }
        List<UserFavResult> data = UserFavConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"userFav:query"})
    @PostMapping("/pageQueryUserFav")
    public Result<PageWrapper<UserFavResult>> pageQueryUserFav(@RequestBody @Validated UserFavPageRequest request) {
        PageWrapper<UserFav> pageWrapper = userFavService.getUserFavPage(request);
        return ResultFactory.success(UserFavConvert.convertPage(pageWrapper));
    }
}
