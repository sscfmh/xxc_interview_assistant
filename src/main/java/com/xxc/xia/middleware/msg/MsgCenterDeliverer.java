package com.xxc.xia.middleware.msg;

import com.xxc.xia.middleware.msg.model.Msg;
import com.xxc.xia.middleware.msg.model.MsgHeader;
import com.xxc.xia.middleware.msg.spi.MsgConsumerSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/10 23:02
 */
@Service
@Slf4j
public class MsgCenterDeliverer implements InitializingBean {

    @Autowired
    private MsgConsumerSpi msgConsumerSpi;

    private boolean sendMsg(String msgType, Object payload, int retryCount) {
        if (MsgCenter.isShutdown.get()) {
            return false;
        }
        MsgHeader msgHeader = new MsgHeader();
        msgHeader.setRetryCnt(retryCount);
        msgHeader.setMsgType(msgType);
        Msg msg = new Msg();
        msg.setMsgHeader(msgHeader);
        msg.setPayload(payload);
        return MsgCenter.MSG_QUEUE.offer(msg);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        MsgCenter.EXECUTOR.submit(() -> {
            do {
                Msg msg = null;
                try {
                    msg = MsgCenter.MSG_QUEUE.take();
                    msgConsumerSpi.subscribe(msg);
                } catch (Exception e) {
                    log.error("消息处理异常", e);
                }
                // 回滚消息
                if (msg != null && msg.getMsgHeader().isRollback()
                    && msg.getMsgHeader().getRetryCnt() < 3) {
                    sendMsg(msg.getMsgHeader().getMsgType(), msg.getPayload(),
                        msg.getMsgHeader().getRetryCnt() + 1);
                }
            } while (!MsgCenter.MSG_QUEUE.isEmpty() || !MsgCenter.isShutdown.get());
        });
    }
}
