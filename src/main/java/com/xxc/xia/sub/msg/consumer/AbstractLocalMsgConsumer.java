package com.xxc.xia.sub.msg.consumer;

import com.xxc.xia.sub.msg.model.MsgInfo;
import lombok.extern.slf4j.Slf4j;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:15
 */
@Slf4j
public abstract class AbstractLocalMsgConsumer implements LocalMsgConsumer {
    @Override
    public MsgConsumeResult consume(MsgInfo msgInfo) {
        MsgConsumeResult msgConsumeResult = new MsgConsumeResult();
        msgConsumeResult.setSuccess(true);
        msgConsumeResult.setNeedRollback(false);
        try {
            log.info("开始处理消息 msgInfo: {}", msgInfo);
            doConsume(msgInfo, msgConsumeResult);
            log.info("消息处理成功 msgInfo: {} msgConsumeResult: {}", msgInfo, msgConsumeResult);
        } catch (Exception e) {
            msgConsumeResult.setSuccess(false);
            msgConsumeResult.setNeedRollback(true);
            handleError(e, msgInfo, msgConsumeResult);
            log.error("消息处理失败 msgInfo: {} msgConsumeResult: {}", msgInfo, msgConsumeResult, e);
        }
        return msgConsumeResult;
    }

    public abstract void doConsume(MsgInfo msgInfo, MsgConsumeResult msgConsumeResult);

    public void handleError(Exception e, MsgInfo msgInfo, MsgConsumeResult msgConsumeResult) {

    }
}
