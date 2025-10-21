package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.enums.BizTypeEnum;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.UserFavConvert;
import com.xxc.xia.dto.userfav.UserFavCreateRequest;
import com.xxc.xia.dto.userfav.UserFavPageRequest;
import com.xxc.xia.dto.userfav.UserFavResult;
import com.xxc.xia.dto.userfav.UserFavUpdateRequest;
import com.xxc.xia.entity.UserFav;
import com.xxc.xia.service.impl.UserFavServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @RequirePermissions(value = { "userFav:create" })
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
    @RequirePermissions(value = { "userFav:delete" })
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
    @RequirePermissions(value = { "userFav:update" })
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
    @RequirePermissions(value = { "userFav:query" })
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
    @RequirePermissions(value = { "userFav:query" })
    @PostMapping("/listQueryUserFavByIds")
    public Result<List<UserFavResult>> listQueryUserFavByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<UserFav> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = userFavService.list();
        } else {
            source = userFavService.getUserFavList(ids);
            ;
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
    @RequirePermissions(value = { "userFav:query" })
    @PostMapping("/pageQueryUserFav")
    public Result<PageWrapper<UserFavResult>> pageQueryUserFav(@RequestBody @Validated UserFavPageRequest request) {
        PageWrapper<UserFav> pageWrapper = userFavService.getUserFavPage(request);
        return ResultFactory.success(UserFavConvert.convertPage(pageWrapper));
    }

    @Operation(summary = "用户收藏(题目/题集)")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/userFavItem")
    public Result<Long> userFavItem(@RequestBody @Validated UserFavCreateRequest request) {
        String userId = LoginContext.getLoginUserId();
        request.setCreateBy(userId);
        request.setUserId(userId);
        Set<String> supportedType = new HashSet<>();
        supportedType.add(BizTypeEnum.FAV_QUESTION.name());
        supportedType.add(BizTypeEnum.FAV_QC.name());
        AssertUtils.isTrue(supportedType.contains(request.getBizType()), "不支持的收藏类型");
        Long id = userFavService.createUserFav(request);
        return ResultFactory.success(id);
    }

    @Operation(summary = "查询用户是否收藏(题目/题集)")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/queryUserFavItem")
    public Result<UserFavResult> queryUserFavItem(@RequestBody @Validated UserFavCreateRequest request) {
        String userId = LoginContext.getLoginUserId();
        Set<String> supportedType = new HashSet<>();
        supportedType.add(BizTypeEnum.FAV_QUESTION.name());
        supportedType.add(BizTypeEnum.FAV_QC.name());
        AssertUtils.isTrue(supportedType.contains(request.getBizType()), "不支持的收藏类型");
        UserFav userFav = userFavService.queryUserFav(BizTypeEnum.getByName(request.getBizType()),
            request.getBizId(), userId);
        return ResultFactory.success(UserFavConvert.convert(userFav));
    }

    @Operation(summary = "用户取消收藏(题目/题集)")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/userCancelFavItem")
    public Result<Boolean> userCancelFavItem(@RequestBody @Validated UserFavCreateRequest request) {
        String userId = LoginContext.getLoginUserId();
        Set<String> supportedType = new HashSet<>();
        supportedType.add(BizTypeEnum.FAV_QUESTION.name());
        supportedType.add(BizTypeEnum.FAV_QC.name());
        AssertUtils.isTrue(supportedType.contains(request.getBizType()), "不支持的收藏类型");
        boolean res = userFavService.cancelFav(BizTypeEnum.getByName(request.getBizType()),
            request.getBizId(), userId);
        return ResultFactory.success(res);
    }
}
