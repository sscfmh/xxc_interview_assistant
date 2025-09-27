package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.*;
import com.xxc.xia.common.wrapper.*;
import com.xxc.xia.convert.QuestionCollectionConvert;
import com.xxc.xia.dto.questioncollection.*;
import com.xxc.xia.entity.QuestionCollection;
import com.xxc.xia.service.impl.QuestionCollectionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 题集 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "题集")
@RestController
@RequestMapping("/api/questionCollection")
public class QuestionCollectionController {


    @Autowired
    private QuestionCollectionServiceImpl questionCollectionService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = {"questionCollection:create"})
    @PostMapping("/createQuestionCollection")
    public Result<String> createQuestionCollection(@RequestBody @Validated QuestionCollectionCreateRequest request) {
        Long id = questionCollectionService.createQuestionCollection(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = {"questionCollection:delete"})
    @PostMapping("/deleteQuestionCollectionById/{id}")
    public Result<Boolean> deleteQuestionCollectionById(@PathVariable("id") Long id) {
        questionCollectionService.deleteQuestionCollection(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = {"questionCollection:update"})
    @PostMapping("/updateQuestionCollectionById")
    public Result<Boolean> updateQuestionCollectionById(@RequestBody @Validated QuestionCollectionUpdateRequest request) {
        questionCollectionService.updateQuestionCollection(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = {"questionCollection:query"})
    @GetMapping("/queryQuestionCollectionById/{id}")
    public Result<QuestionCollectionResult> queryQuestionCollectionById(@PathVariable("id") Long id) {
        QuestionCollection questionCollection = questionCollectionService.getQuestionCollection(id);
        return ResultFactory.success(QuestionCollectionConvert.convert(questionCollection));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = {"questionCollection:query"})
    @PostMapping("/listQueryQuestionCollectionByIds")
    public Result<List<QuestionCollectionResult>> listQueryQuestionCollectionByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<QuestionCollection> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionCollectionService.list();
        } else {
            source = questionCollectionService.getQuestionCollectionList(ids);;
        }
        List<QuestionCollectionResult> data = QuestionCollectionConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = {"questionCollection:query"})
    @PostMapping("/pageQueryQuestionCollection")
    public Result<PageWrapper<QuestionCollectionResult>> pageQueryQuestionCollection(@RequestBody @Validated QuestionCollectionPageRequest request) {
        PageWrapper<QuestionCollection> pageWrapper = questionCollectionService.getQuestionCollectionPage(request);
        return ResultFactory.success(QuestionCollectionConvert.convertPage(pageWrapper));
    }
}
