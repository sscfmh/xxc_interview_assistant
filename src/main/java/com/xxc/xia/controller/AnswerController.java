package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.AnswerConvert;
import com.xxc.xia.dto.answer.AnswerCreateRequest;
import com.xxc.xia.dto.answer.AnswerPageRequest;
import com.xxc.xia.dto.answer.AnswerResult;
import com.xxc.xia.dto.answer.AnswerUpdateRequest;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.service.impl.AnswerServiceImpl;
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

import java.util.List;

/**
 * 答案 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "答案")
@RestController
@RequestMapping("/api/answer")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl answerService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "answer:create" })
    @PostMapping("/createAnswer")
    public Result<String> createAnswer(@RequestBody @Validated AnswerCreateRequest request) {
        Long id = answerService.createAnswer(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = { "answer:delete" })
    @PostMapping("/deleteAnswerById/{id}")
    public Result<Boolean> deleteAnswerById(@PathVariable("id") Long id) {
        answerService.deleteAnswer(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = { "answer:update" })
    @PostMapping("/updateAnswerById")
    public Result<Boolean> updateAnswerById(@RequestBody @Validated AnswerUpdateRequest request) {
        answerService.updateAnswer(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = { "answer:query" })
    @GetMapping("/queryAnswerById/{id}")
    public Result<AnswerResult> queryAnswerById(@PathVariable("id") Long id) {
        Answer answer = answerService.getAnswer(id);
        return ResultFactory.success(AnswerConvert.convert(answer));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = { "answer:query" })
    @PostMapping("/listQueryAnswerByIds")
    public Result<List<AnswerResult>> listQueryAnswerByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Answer> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = answerService.list();
        } else {
            source = answerService.getAnswerList(ids);
            ;
        }
        List<AnswerResult> data = AnswerConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = { "answer:query" })
    @PostMapping("/pageQueryAnswer")
    public Result<PageWrapper<AnswerResult>> pageQueryAnswer(@RequestBody @Validated AnswerPageRequest request) {
        PageWrapper<Answer> pageWrapper = answerService.getAnswerPage(request);
        return ResultFactory.success(AnswerConvert.convertPage(pageWrapper));
    }

    /**
     * 用户提交问题
     *
     * @param request
     * @return
     */
    @Operation(summary = "用户提交问题")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/userAnswerQuestion")
    public Result<String> userAnswerQuestion(@RequestBody @Validated AnswerCreateRequest request) {
        String userId = LoginContext.getLoginUserId();
        request.setUserId(userId);
        request.setNeedSendAnswerQuestionMsg(true);
        request.setCreateBy(userId);
        request.setUpdateBy(userId);
        AssertUtils.notBlank(request.getContent(), "内容不能为空");
        AssertUtils.notBlank(request.getQuestionId(), "题目ID不能为空");
        Long id = answerService.createAnswer(request);
        return ResultFactory.success(String.valueOf(id));
    }
}
