package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.QuestionQcRelConvert;
import com.xxc.xia.dto.questionqcrel.QuestionQcRelCreateRequest;
import com.xxc.xia.dto.questionqcrel.QuestionQcRelPageRequest;
import com.xxc.xia.dto.questionqcrel.QuestionQcRelResult;
import com.xxc.xia.dto.questionqcrel.QuestionQcRelUpdateRequest;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.entity.Question;
import com.xxc.xia.entity.QuestionQcRel;
import com.xxc.xia.service.impl.AnswerServiceImpl;
import com.xxc.xia.service.impl.QuestionQcRelServiceImpl;
import com.xxc.xia.service.impl.QuestionServiceImpl;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 题目题集关联 controller
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Tag(name = "题目题集关联")
@RestController
@RequestMapping("/api/questionQcRel")
public class QuestionQcRelController {

    @Autowired
    private QuestionQcRelServiceImpl questionQcRelService;

    @Autowired
    private QuestionServiceImpl      questionService;

    @Autowired
    private AnswerServiceImpl        answerService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "questionQcRel:create" })
    @PostMapping("/createQuestionQcRel")
    public Result<String> createQuestionQcRel(@RequestBody @Validated QuestionQcRelCreateRequest request) {
        Long id = questionQcRelService.createQuestionQcRel(request);
        return ResultFactory.success(String.valueOf(id));
    }

    /**
     * 删除一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "删除一个")
    @RequirePermissions(value = { "questionQcRel:delete" })
    @PostMapping("/deleteQuestionQcRelById/{id}")
    public Result<Boolean> deleteQuestionQcRelById(@PathVariable("id") Long id) {
        questionQcRelService.deleteQuestionQcRel(id);
        return ResultFactory.success(true);
    }

    /**
     * 修改一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "修改一个")
    @RequirePermissions(value = { "questionQcRel:update" })
    @PostMapping("/updateQuestionQcRelById")
    public Result<Boolean> updateQuestionQcRelById(@RequestBody @Validated QuestionQcRelUpdateRequest request) {
        questionQcRelService.updateQuestionQcRel(request);
        return ResultFactory.success(true);
    }

    /**
     * 查询一个
     *
     * @param id
     * @return
     */
    @Operation(summary = "查询一个")
    @RequirePermissions(value = { "questionQcRel:query" })
    @GetMapping("/queryQuestionQcRelById/{id}")
    public Result<QuestionQcRelResult> queryQuestionQcRelById(@PathVariable("id") Long id) {
        QuestionQcRel questionQcRel = questionQcRelService.getQuestionQcRel(id);
        return ResultFactory.success(QuestionQcRelConvert.convert(questionQcRel));
    }

    /**
     * 列表查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "列表查询")
    @RequirePermissions(value = { "questionQcRel:query" })
    @PostMapping("/listQueryQuestionQcRelByIds")
    public Result<List<QuestionQcRelResult>> listQueryQuestionQcRelByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<QuestionQcRel> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionQcRelService.list();
        } else {
            source = questionQcRelService.getQuestionQcRelList(ids);
        }
        List<QuestionQcRelResult> data = QuestionQcRelConvert.convertList(source);
        return ResultFactory.success(data);
    }

    /**
     * 分页查询
     *
     * @param request
     * @return
     */
    @Operation(summary = "分页查询")
    @RequirePermissions(value = { "questionQcRel:query" })
    @PostMapping("/pageQueryQuestionQcRel")
    public Result<PageWrapper<QuestionQcRelResult>> pageQueryQuestionQcRel(@RequestBody @Validated QuestionQcRelPageRequest request) {
        PageWrapper<QuestionQcRel> pageWrapper = questionQcRelService.getQuestionQcRelPage(request);
        PageWrapper<QuestionQcRelResult> result = QuestionQcRelConvert.convertPage(pageWrapper);
        if (result.getTotal() > 0) {
            // 获取题目信息
            for (QuestionQcRelResult item : result.getData()) {
                Question question = questionService.getQuestion(Long.valueOf(item.getQuestionId()));
                if (question != null) {
                    item.setTitle(question.getTitle());
                    item.setContent(question.getContent());
                    item.setQuestionLevel(question.getQuestionLevel());
                }
            }
            // 获取用户答题信息
            if (request.isNeedAlreadyAnswerFlag() && LoginContext.isLogin()) {
                String userId = LoginContext.getLoginUserId();
                List<String> questionIds = pageWrapper.getData().stream()
                    .map(QuestionQcRel::getQuestionId).map(String::valueOf).distinct().toList();
                List<Answer> answerList = answerService.queryByUserIdAndQuestionIds(userId,
                    questionIds);
                Set<String> alreadyAnswerQuestionIds = answerList.stream()
                    .map(Answer::getQuestionId).collect(Collectors.toSet());
                for (QuestionQcRelResult item : result.getData()) {
                    item.setAlreadyAnswer(alreadyAnswerQuestionIds.contains(item.getQuestionId()));
                }
            }
        }
        return ResultFactory.success(result);
    }
}
