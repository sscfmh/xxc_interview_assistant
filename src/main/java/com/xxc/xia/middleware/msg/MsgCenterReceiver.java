package com.xxc.xia.middleware.msg;

import com.xxc.xia.middleware.msg.model.Msg;
import com.xxc.xia.middleware.msg.model.MsgHeader;
import org.springframework.stereotype.Service;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/10 23:01
 */
@Service
public class MsgCenterReceiver {
    /**
     * 发送消息
     * @param msgType
     * @param payload
     * @return
     */
    public boolean sendMsg(String msgType, Object payload) {
        return sendMsg(msgType, payload, 0);
    }

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
}
