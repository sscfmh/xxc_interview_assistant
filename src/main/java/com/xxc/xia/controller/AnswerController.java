package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.AnswerConvert;
import com.xxc.xia.dto.answer.*;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.service.impl.AnswerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
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
    @RequirePermissions(value = {"answer:create"})
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
    @RequirePermissions(value = {"answer:delete"})
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
    @RequirePermissions(value = {"answer:update"})
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
    @RequirePermissions(value = {"answer:query"})
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
    @RequirePermissions(value = {"answer:query"})
    @PostMapping("/listQueryAnswerByIds")
    public Result<List<AnswerResult>> listQueryAnswerByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Answer> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = answerService.list();
        } else {
            source = answerService.getAnswerList(ids);;
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
    @RequirePermissions(value = {"answer:query"})
    @PostMapping("/pageQueryAnswer")
    public Result<PageWrapper<AnswerResult>> pageQueryAnswer(@RequestBody @Validated AnswerPageRequest request) {
        PageWrapper<Answer> pageWrapper = answerService.getAnswerPage(request);
        return ResultFactory.success(AnswerConvert.convertPage(pageWrapper));
    }
}
