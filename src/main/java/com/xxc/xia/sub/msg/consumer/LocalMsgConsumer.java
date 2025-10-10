package com.xxc.xia.sub.msg.consumer;

import com.xxc.xia.common.enums.MsgTypeEnum;
import com.xxc.xia.middleware.msg.model.MsgHeader;
import com.xxc.xia.sub.msg.model.MsgInfo;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:06
 */
public interface LocalMsgConsumer {
    MsgTypeEnum getMsgType();
    MsgConsumeResult consume(MsgInfo msgInfo);
}
