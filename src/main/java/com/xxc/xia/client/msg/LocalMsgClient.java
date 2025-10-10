package com.xxc.xia.client.msg;

import com.alibaba.fastjson.JSON;
import com.xxc.xia.common.enums.MsgTypeEnum;
import com.xxc.xia.middleware.msg.MsgCenterReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:02
 */
@Service
public class LocalMsgClient {

    @Autowired
    private MsgCenterReceiver msgCenterReceiver;

    /**
     * 发送消息
     * @param msgType
     * @param payload
     * @return
     */
    public boolean sendMsg(MsgTypeEnum msgType, Object payload) {
        return msgCenterReceiver.sendMsg(msgType.name(),
            payload instanceof String ? payload : JSON.toJSONString(payload));
    }
}
