package com.xxc.xia.middleware.msg.spi;

import com.xxc.xia.middleware.msg.model.Msg;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/10 22:42
 */
public interface MsgConsumerSpi {
    void subscribe(Msg msg);
}
