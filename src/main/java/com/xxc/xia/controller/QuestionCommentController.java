package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.QuestionCommentConvert;
import com.xxc.xia.dto.questioncomment.*;
import com.xxc.xia.entity.QuestionComment;
import com.xxc.xia.service.impl.QuestionCommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 问题评论 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "问题评论")
@RestController
@RequestMapping("/api/questionComment")
public class QuestionCommentController {


    @Autowired
    private QuestionCommentServiceImpl questionCommentService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"questionComment:create"})
    @PostMapping("/createQuestionComment")
    public Result<String> createQuestionComment(@RequestBody @Validated QuestionCommentCreateRequest request) {
        Long id = questionCommentService.createQuestionComment(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"questionComment:delete"})
    @PostMapping("/deleteQuestionCommentById/{id}")
    public Result<Boolean> deleteQuestionCommentById(@PathVariable("id") Long id) {
        questionCommentService.deleteQuestionComment(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"questionComment:update"})
    @PostMapping("/updateQuestionCommentById")
    public Result<Boolean> updateQuestionCommentById(@RequestBody @Validated QuestionCommentUpdateRequest request) {
        questionCommentService.updateQuestionComment(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"questionComment:query"})
    @GetMapping("/queryQuestionCommentById/{id}")
    public Result<QuestionCommentResult> queryQuestionCommentById(@PathVariable("id") Long id) {
        QuestionComment questionComment = questionCommentService.getQuestionComment(id);
        return ResultFactory.success(QuestionCommentConvert.convert(questionComment));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"questionComment:query"})
    @PostMapping("/listQueryQuestionCommentByIds")
    public Result<List<QuestionCommentResult>> listQueryQuestionCommentByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<QuestionComment> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionCommentService.list();
        } else {
            source = questionCommentService.getQuestionCommentList(ids);;
        }
        List<QuestionCommentResult> data = QuestionCommentConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"questionComment:query"})
    @PostMapping("/pageQueryQuestionComment")
    public Result<PageWrapper<QuestionCommentResult>> pageQueryQuestionComment(@RequestBody @Validated QuestionCommentPageRequest request) {
        PageWrapper<QuestionComment> pageWrapper = questionCommentService.getQuestionCommentPage(request);
        return ResultFactory.success(QuestionCommentConvert.convertPage(pageWrapper));
    }
}
