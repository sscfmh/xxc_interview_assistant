package com.xxc.xia.controller;

import com.xxc.xia.common.annotation.RequirePermissions;
import com.xxc.xia.common.context.LoginContext;
import com.xxc.xia.common.context.UserLoginInfo;
import com.xxc.xia.common.request.ListRequest;
import com.xxc.xia.common.result.Result;
import com.xxc.xia.common.result.ResultFactory;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.QuestionCommentConvert;
import com.xxc.xia.dto.questioncomment.QuestionCommentBaseDto;
import com.xxc.xia.dto.questioncomment.QuestionCommentCreateRequest;
import com.xxc.xia.dto.questioncomment.QuestionCommentPageRequest;
import com.xxc.xia.dto.questioncomment.QuestionCommentResult;
import com.xxc.xia.dto.questioncomment.QuestionCommentUpdateRequest;
import com.xxc.xia.entity.QuestionComment;
import com.xxc.xia.entity.User;
import com.xxc.xia.service.impl.QuestionCommentServiceImpl;
import com.xxc.xia.service.impl.UserServiceImpl;
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
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    private UserServiceImpl            userService;

    /**
     * 创建一个
     *
     * @param request
     * @return
     */
    @Operation(summary = "创建一个")
    @RequirePermissions(value = { "questionComment:create" })
    @PostMapping("/createQuestionComment")
    public Result<String> createQuestionComment(@RequestBody @Validated QuestionCommentCreateRequest request) {
        UserLoginInfo userLoginInfo = LoginContext.getUserLoginInfo();
        if (!userLoginInfo.isSuperAdmin()) {
            request.setUserId(String.valueOf(LoginContext.getLoginUserId()));
        }

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
    @RequirePermissions(value = { "questionComment:delete" })
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
    @RequirePermissions(value = { "questionComment:update" })
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
    @RequirePermissions(value = { "questionComment:query" })
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
    @RequirePermissions(value = { "questionComment:query" })
    @PostMapping("/listQueryQuestionCommentByIds")
    public Result<List<QuestionCommentResult>> listQueryQuestionCommentByIds(@RequestBody ListRequest request) {
        List<Long> ids = request.getIds();
        List<QuestionComment> source = null;
        if (CollectionUtils.isEmpty(ids)) {
            source = questionCommentService.list();
        } else {
            source = questionCommentService.getQuestionCommentList(ids);
            ;
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
    @RequirePermissions(value = { "questionComment:query" })
    @PostMapping("/pageQueryQuestionComment")
    public Result<PageWrapper<QuestionCommentResult>> pageQueryQuestionComment(@RequestBody @Validated QuestionCommentPageRequest request) {
        PageWrapper<QuestionComment> pageWrapper = questionCommentService
            .getQuestionCommentPage(request);
        PageWrapper<QuestionCommentResult> data = QuestionCommentConvert.convertPage(pageWrapper);
        if (data.getData().isEmpty()) {
            return ResultFactory.success(data);
        }
        if (request.isNeedUserInfo()) {
            List<Long> userIds = data.getData().stream().map(QuestionCommentBaseDto::getUserId)
                .filter(Objects::nonNull).map(Long::valueOf).distinct().toList();
            List<User> userList = userService.listByIds(userIds);
            Map<String, User> userIdMap = userList.stream()
                .collect(Collectors.toMap(x -> String.valueOf(x.getId()), x -> x, (o1, o2) -> o1));
            for (QuestionCommentResult datum : data.getData()) {
                User user = userIdMap.get(datum.getUserId());
                if (user != null) {
                    datum.setAvatar(user.getAvatar());
                    datum.setNickName(user.getNickName());
                }
            }

        }
        return ResultFactory.success(data);
    }

    @Operation(summary = "增加点赞数")
    @RequirePermissions(value = { "common:login" })
    @PostMapping("/addQuestionCommentHeartCnt")
    public Result<QuestionCommentResult> addQuestionCommentHeartCnt(@RequestBody @Validated QuestionCommentUpdateRequest request) {
        boolean res = questionCommentService.addHeartCnt(request.getId(), 1);
        if (!res) {
            return ResultFactory.error("操作失败");
        }
        QuestionComment questionComment = questionCommentService
            .getQuestionComment(request.getId());
        return ResultFactory.success(QuestionCommentConvert.convert(questionComment));
    }
}
