package com.xxc.xia.sub.msg.consumer;

import com.alibaba.fastjson.JSON;
import com.xxc.xia.common.enums.BizTypeEnum;
import com.xxc.xia.common.enums.MsgTypeEnum;
import com.xxc.xia.entity.Answer;
import com.xxc.xia.service.impl.SignInRecordServiceImpl;
import com.xxc.xia.sub.msg.model.MsgInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:11
 */
@Service
@Slf4j
public class SignInAnswerQuestionMsgConsumer extends AbstractLocalMsgConsumer {

    @Autowired
    private SignInRecordServiceImpl signInRecordService;

    @Override
    public MsgTypeEnum getMsgType() {
        return MsgTypeEnum.USER_ANSWER_QUESTION;
    }

    @Override
    public void doConsume(MsgInfo msgInfo, MsgConsumeResult msgConsumeResult) {
        log.info("用户解答问题消息");
        // 更新今日打卡记录
        Answer answer = JSON.parseObject((String) msgInfo.getPayload(), Answer.class);
        signInRecordService.createRecord(BizTypeEnum.USER_AQ_PER_DAY, answer.getUserId(),
            answer.getUpdateTime());
    }
}
