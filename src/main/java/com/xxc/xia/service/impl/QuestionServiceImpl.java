package com.xxc.xia.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.common.enums.Logical;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.QuestionConvert;
import com.xxc.xia.dto.question.QuestionCreateRequest;
import com.xxc.xia.dto.question.QuestionPageRequest;
import com.xxc.xia.dto.question.QuestionUpdateRequest;
import com.xxc.xia.entity.Question;
import com.xxc.xia.mapper.QuestionMapper;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 题目 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> {

    @Resource
    private QuestionMapper questionMapper;

    /**
     * 创建Question
     *
     * @param request
     * @return
     */
    public Long createQuestion(QuestionCreateRequest request) {
        // 插入
        Question question = QuestionConvert.convert(request);
        question.setCreateTime(new Date());
        question.setUpdateTime(new Date());
        questionMapper.insert(question);
        // 返回
        return question.getId();
    }

    /**
     * 更新Question
     *
     * @param request
     * @return
     */
    public void updateQuestion(QuestionUpdateRequest request) {
        // 校验存在
        checkQuestionExists(request.getId());
        // 更新
        Question updateObj = QuestionConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        questionMapper.updateById(updateObj);
    }

    /**
     * 删除Question
     *
     * @param id
     * @return
     */
    public void deleteQuestion(Long id) {
        // 校验存在
        checkQuestionExists(id);
        // 删除
        questionMapper.deleteById(id);
    }

    /**
     * 校验Question是否存在
     *
     * @param id
     */
    private void checkQuestionExists(Long id) {
        Question question = questionMapper.selectById(id);
        AssertUtils.notNull(question, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取Question
     *
     * @param id
     * @return
     */
    public Question getQuestion(Long id) {
        return questionMapper.selectById(id);
    }

    /**
     * 获取Question列表
     *
     * @param ids
     * @return
     */
    public List<Question> getQuestionList(Collection<Long> ids) {
        return questionMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取Question列表
     *
     * @param request
     * @return
     */
    public PageWrapper<Question> getQuestionPage(QuestionPageRequest request) {
        LambdaQueryWrapper<Question> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, Question::getId, request.getId());
        // 标题
        lqw.eq(StringUtils.isNotBlank(request.getTitle()), Question::getTitle, request.getTitle());
        // 内容
        lqw.eq(StringUtils.isNotBlank(request.getContent()), Question::getContent,
            request.getContent());
        // 参考答案
        lqw.eq(StringUtils.isNotBlank(request.getRefAnswer()), Question::getRefAnswer,
            request.getRefAnswer());
        // 创建来源
        lqw.eq(StringUtils.isNotBlank(request.getCreateSource()), Question::getCreateSource,
            request.getCreateSource());
        // 用户ID
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), Question::getUserId,
            request.getUserId());
        // 题目等级
        lqw.eq(StringUtils.isNotBlank(request.getQuestionLevel()), Question::getQuestionLevel,
            request.getQuestionLevel());
        // 标签
        lqw.eq(StringUtils.isNotBlank(request.getTags()), Question::getTags, request.getTags());
        // 题号
        lqw.eq(request.getQuestionNo() != null, Question::getQuestionNo, request.getQuestionNo());
        // 访问量
        lqw.eq(request.getViewCnt() != null, Question::getViewCnt, request.getViewCnt());
        // 提交答案量
        lqw.eq(request.getCommitAnswerCnt() != null, Question::getCommitAnswerCnt,
            request.getCommitAnswerCnt());
        // 收藏量
        lqw.eq(request.getFavCnt() != null, Question::getFavCnt, request.getFavCnt());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), Question::getExtendInfo,
            request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), Question::getCreateBy,
            request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, Question::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, Question::getCreateTime,
            request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, Question::getCreateTime,
            request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), Question::getUpdateBy,
            request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, Question::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, Question::getUpdateTime,
            request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, Question::getUpdateTime,
            request.getUpdateTimeEnd());
        if (CollectionUtils.isNotEmpty(request.getTagIds())) {
            if (Logical.AND.name().equalsIgnoreCase(request.getTagIdsOpType())) {
                lqw.and(qw -> {
                    for (String tagId : request.getTagIds()) {
                        qw.apply("find_in_set({0}, `tags`)", tagId);
                    }
                });
            } else {
                lqw.and(qw -> {
                    for (String tagId : request.getTagIds()) {
                        qw.or().apply("find_in_set({0}, `tags`)", tagId);
                    }
                });
            }

        }
        lqw.orderByDesc(Question::getId);
        return questionMapper.selectPage(request, lqw);
    }

}