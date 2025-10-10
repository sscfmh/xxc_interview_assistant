package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.QuestionConvert;
import com.xxc.xia.dto.question.QuestionCreateRequest;
import com.xxc.xia.dto.question.QuestionPageRequest;
import com.xxc.xia.dto.question.QuestionResult;
import com.xxc.xia.dto.question.QuestionUpdateRequest;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.entity.Question;
import com.xxc.xia.service.impl.AnswerServiceImpl;
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

    @Autowired
    private AnswerServiceImpl   answerService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "question:create" })
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
    @RequirePermissions(value = { "question:delete" })
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
    @RequirePermissions(value = { "question:update" })
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
    @RequirePermissions(value = { "question:query" })
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
    @RequirePermissions(value = { "question:query" })
    @PostMapping("/listQueryQuestionByIds")
    public Result<List<QuestionResult>> listQueryQuestionByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<Question> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionService.list();
        } else {
            source = questionService.getQuestionList(ids);
            ;
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
    @RequirePermissions(value = { "question:query" })
    @PostMapping("/pageQueryQuestion")
    public Result<PageWrapper<QuestionResult>> pageQueryQuestion(@RequestBody @Validated QuestionPageRequest request) {
        PageWrapper<Question> pageWrapper = questionService.getQuestionPage(request);
        PageWrapper<QuestionResult> data = QuestionConvert.convertPage(pageWrapper);
        if (pageWrapper.getTotal() > 0 && request.isNeedAlreadyAnswerFlag()
            && LoginContext.isLogin()) {
            String userId = LoginContext.getLoginUserId();
            List<String> questionIds = pageWrapper.getData().stream().map(Question::getId)
                .map(String::valueOf).distinct().toList();
            List<Answer> answerList = answerService.queryByUserIdAndQuestionIds(userId,
                questionIds);
            Set<String> alreadyAnswerQuestionIds = answerList.stream().map(Answer::getQuestionId)
                .collect(Collectors.toSet());
            for (QuestionResult item : data.getData()) {
                item.setAlreadyAnswer(
                    alreadyAnswerQuestionIds.contains(String.valueOf(item.getId())));
            }
        }
        return ResultFactory.success(data);
    }
}
