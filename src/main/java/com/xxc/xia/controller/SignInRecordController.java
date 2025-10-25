package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.enums.BizTypeEnum;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.SignInRecordConvert;
import com.xxc.xia.dto.signinrecord.QueryUserAQSignInRequest;
import com.xxc.xia.dto.signinrecord.SignInRecordCreateRequest;
import com.xxc.xia.dto.signinrecord.SignInRecordPageRequest;
import com.xxc.xia.dto.signinrecord.SignInRecordResult;
import com.xxc.xia.dto.signinrecord.SignInRecordUpdateRequest;
import com.xxc.xia.entity.SignInRecord;
import com.xxc.xia.service.impl.SignInRecordServiceImpl;
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

import java.util.Date;
import java.util.List;

/**
 * 签到记录 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "签到记录")
@RestController
@RequestMapping("/api/signInRecord")
public class SignInRecordController {

    @Autowired
    private SignInRecordServiceImpl signInRecordService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "signInRecord:create" })
    @PostMapping("/createSignInRecord")
    public Result<String> createSignInRecord(@RequestBody @Validated SignInRecordCreateRequest request) {
        Long id = signInRecordService.createSignInRecord(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = { "signInRecord:delete" })
    @PostMapping("/deleteSignInRecordById/{id}")
    public Result<Boolean> deleteSignInRecordById(@PathVariable("id") Long id) {
        signInRecordService.deleteSignInRecord(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = { "signInRecord:update" })
    @PostMapping("/updateSignInRecordById")
    public Result<Boolean> updateSignInRecordById(@RequestBody @Validated SignInRecordUpdateRequest request) {
        signInRecordService.updateSignInRecord(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = { "signInRecord:query" })
    @GetMapping("/querySignInRecordById/{id}")
    public Result<SignInRecordResult> querySignInRecordById(@PathVariable("id") Long id) {
        SignInRecord signInRecord = signInRecordService.getSignInRecord(id);
        return ResultFactory.success(SignInRecordConvert.convert(signInRecord));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = { "signInRecord:query" })
    @PostMapping("/listQuerySignInRecordByIds")
    public Result<List<SignInRecordResult>> listQuerySignInRecordByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<SignInRecord> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = signInRecordService.list();
        } else {
            source = signInRecordService.getSignInRecordList(ids);
            ;
        }
        List<SignInRecordResult> data = SignInRecordConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = { "signInRecord:query" })
    @PostMapping("/pageQuerySignInRecord")
    public Result<PageWrapper<SignInRecordResult>> pageQuerySignInRecord(@RequestBody @Validated SignInRecordPageRequest request) {
        PageWrapper<SignInRecord> pageWrapper = signInRecordService.getSignInRecordPage(request);
        return ResultFactory.success(SignInRecordConvert.convertPage(pageWrapper));
    }

    @Operation(summary = "查询本月用户答题签到记录")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/queryUserAQSignIn")
    public Result<SignInRecordResult> queryUserAQSignIn(@RequestBody @Validated QueryUserAQSignInRequest request) {
        if (request.getQueryTime() == null) {
            request.setQueryTime(new Date());
        }
        SignInRecord signInRecord = signInRecordService.queryByUK(BizTypeEnum.USER_AQ_PER_DAY,
            LoginContext.getLoginUserId(), request.getQueryTime());
        return ResultFactory.success(SignInRecordConvert.convert(signInRecord));
    }

}
