package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.QuestionConvert;
import com.xxc.xia.dto.question.*;
import com.xxc.xia.entity.Question;
import com.xxc.xia.service.impl.QuestionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 题目 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "题目")
@RestController
@RequestMapping("/api/question")
public class QuestionController {


    @Autowired
    private QuestionServiceImpl questionService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"question:create"})
    @PostMapping("/createQuestion")
    public Result<String> createQuestion(@RequestBody @Validated QuestionCreateRequest request) {
        Long id = questionService.createQuestion(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"question:delete"})
    @PostMapping("/deleteQuestionById/{id}")
    public Result<Boolean> deleteQuestionById(@PathVariable("id") Long id) {
        questionService.deleteQuestion(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"question:update"})
    @PostMapping("/updateQuestionById")
    public Result<Boolean> updateQuestionById(@RequestBody @Validated QuestionUpdateRequest request) {
        questionService.updateQuestion(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"question:query"})
    @GetMapping("/queryQuestionById/{id}")
    public Result<QuestionResult> queryQuestionById(@PathVariable("id") Long id) {
        Question question = questionService.getQuestion(id);
        return ResultFactory.success(QuestionConvert.convert(question));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"question:query"})
    @PostMapping("/listQueryQuestionByIds")
    public Result<List<QuestionResult>> listQueryQuestionByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Question> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionService.list();
        } else {
            source = questionService.getQuestionList(ids);;
        }
        List<QuestionResult> data = QuestionConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"question:query"})
    @PostMapping("/pageQueryQuestion")
    public Result<PageWrapper<QuestionResult>> pageQueryQuestion(@RequestBody @Validated QuestionPageRequest request) {
        PageWrapper<Question> pageWrapper = questionService.getQuestionPage(request);
        return ResultFactory.success(QuestionConvert.convertPage(pageWrapper));
    }
}
