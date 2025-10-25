package com.xxc.xia.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxc.xia.client.msg.LocalMsgClient;
import com.xxc.xia.common.enums.MsgTypeEnum;
import com.xxc.xia.common.utils.AssertUtils;
import com.xxc.xia.common.wrapper.PageWrapper;
import com.xxc.xia.convert.AnswerConvert;
import com.xxc.xia.dto.answer.AnswerCreateRequest;
import com.xxc.xia.dto.answer.AnswerPageRequest;
import com.xxc.xia.dto.answer.AnswerUpdateRequest;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.mapper.AnswerMapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 答案 serviceImpl
 *
 * @author xxc
 * @create 2025-09-27 12:09:35
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> {

    @Resource
    private AnswerMapper        answerMapper;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private LocalMsgClient      localMsgClient;

    /**
     * 创建Answer
     *
     * @param request
     * @return
     */
    public Long createAnswer(AnswerCreateRequest request) {
        // 插入
        Answer answer = AnswerConvert.convert(request);
        answer.setCreateTime(new Date());
        answer.setUpdateTime(new Date());
        transactionTemplate.executeWithoutResult(status -> {
            answerMapper.insert(answer);
            if (request.isNeedSendAnswerQuestionMsg()) {
                JSONObject msg = (JSONObject) JSON.toJSON(answer);
                localMsgClient.sendMsg(MsgTypeEnum.USER_ANSWER_QUESTION, msg);
            }
        });

        // 返回
        return answer.getId();
    }

    /**
     * 更新Answer
     *
     * @param request
     * @return
     */
    public void updateAnswer(AnswerUpdateRequest request) {
        // 校验存在
        checkAnswerExists(request.getId());
        // 更新
        Answer updateObj = AnswerConvert.convert(request);
        updateObj.setUpdateTime(new Date());
        transactionTemplate.executeWithoutResult(status -> {
            answerMapper.updateById(updateObj);
            if (request.isNeedSendAnswerQuestionMsg()) {
                JSONObject msg = (JSONObject) JSON.toJSON(updateObj);
                localMsgClient.sendMsg(MsgTypeEnum.USER_ANSWER_QUESTION, msg);
            }
        });
    }

    /**
     * 删除Answer
     *
     * @param id
     * @return
     */
    public void deleteAnswer(Long id) {
        // 校验存在
        checkAnswerExists(id);
        // 删除
        answerMapper.deleteById(id);
    }

    /**
     * 校验Answer是否存在
     *
     * @param id
     */
    private void checkAnswerExists(Long id) {
        Answer answer = answerMapper.selectById(id);
        AssertUtils.notNull(answer, String.format("不存在id=%d的记录", id));
    }

    /**
     * 获取Answer
     *
     * @param id
     * @return
     */
    public Answer getAnswer(Long id) {
        return answerMapper.selectById(id);
    }

    /**
     * 获取Answer列表
     *
     * @param ids
     * @return
     */
    public List<Answer> getAnswerList(Collection<Long> ids) {
        return answerMapper.selectBatchIds(ids);
    }

    /**
     * 分页获取Answer列表
     *
     * @param request
     * @return
     */
    public PageWrapper<Answer> getAnswerPage(AnswerPageRequest request) {
        LambdaQueryWrapper<Answer> lqw = new LambdaQueryWrapper<>();
        // 主键ID
        lqw.eq(request.getId() != null, Answer::getId, request.getId());
        // 题目ID
        lqw.eq(StringUtils.isNotBlank(request.getQuestionId()), Answer::getQuestionId,
            request.getQuestionId());
        // 用户ID
        lqw.eq(StringUtils.isNotBlank(request.getUserId()), Answer::getUserId, request.getUserId());
        // 内容
        lqw.eq(StringUtils.isNotBlank(request.getContent()), Answer::getContent,
            request.getContent());
        // 扩展信息
        lqw.eq(StringUtils.isNotBlank(request.getExtendInfo()), Answer::getExtendInfo,
            request.getExtendInfo());
        // 创建人
        lqw.eq(StringUtils.isNotBlank(request.getCreateBy()), Answer::getCreateBy,
            request.getCreateBy());
        // 创建时间
        lqw.eq(request.getCreateTime() != null, Answer::getCreateTime, request.getCreateTime());
        // 创建时间 start
        lqw.ge(request.getCreateTimeStart() != null, Answer::getCreateTime,
            request.getCreateTimeStart());
        // 创建时间 end
        lqw.le(request.getCreateTimeEnd() != null, Answer::getCreateTime,
            request.getCreateTimeEnd());
        // 修改人
        lqw.eq(StringUtils.isNotBlank(request.getUpdateBy()), Answer::getUpdateBy,
            request.getUpdateBy());
        // 修改时间
        lqw.eq(request.getUpdateTime() != null, Answer::getUpdateTime, request.getUpdateTime());
        // 修改时间 start
        lqw.ge(request.getUpdateTimeStart() != null, Answer::getUpdateTime,
            request.getUpdateTimeStart());
        // 修改时间 end
        lqw.le(request.getUpdateTimeEnd() != null, Answer::getUpdateTime,
            request.getUpdateTimeEnd());
        lqw.orderByDesc(Answer::getId);
        return answerMapper.selectPage(request, lqw);
    }

    /**
     * 根据用户ID和题目ID列表查询
     *
     * @param userId
     * @param questionIds
     * @return
     */
    public List<Answer> queryByUserIdAndQuestionIds(String userId, List<String> questionIds) {
        return new LambdaQueryChainWrapper<>(baseMapper).eq(Answer::getUserId, userId)
            .in(Answer::getQuestionId, questionIds).list();
    }

    /**
     * userCommitQuestionAnswer
     * @param request
     * @return
     */
    public Long userCommitQuestionAnswer(AnswerCreateRequest request) {
        Answer dbAnswer = new LambdaQueryChainWrapper<>(baseMapper)
            .eq(Answer::getUserId, request.getUserId())
            .eq(Answer::getQuestionId, request.getQuestionId()).one();
        if (dbAnswer != null) {
            AnswerUpdateRequest updateRequest = new AnswerUpdateRequest();
            updateRequest.setId(dbAnswer.getId());
            updateRequest.setUserId(request.getUserId());
            updateRequest.setContent(request.getContent());
            updateRequest.setNeedSendAnswerQuestionMsg(true);
            updateAnswer(updateRequest);
            return dbAnswer.getId();
        }
        try {
            return createAnswer(request);
        } catch (DuplicateKeyException e) {
            dbAnswer = new LambdaQueryChainWrapper<>(baseMapper)
                .eq(Answer::getUserId, request.getUserId())
                .eq(Answer::getQuestionId, request.getQuestionId()).one();
            AssertUtils.notNull(dbAnswer, "answer 不存在");
            AnswerUpdateRequest updateRequest = new AnswerUpdateRequest();
            updateRequest.setId(dbAnswer.getId());
            updateRequest.setUserId(request.getUserId());
            updateRequest.setContent(request.getContent());
            updateAnswer(updateRequest);
            return dbAnswer.getId();
        }
    }

}