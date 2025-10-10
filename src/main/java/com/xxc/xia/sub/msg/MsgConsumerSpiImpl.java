package com.xxc.xia.sub.msg;

import com.xxc.xia.common.enums.MsgTypeEnum;
import com.xxc.xia.middleware.msg.model.Msg;
import com.xxc.xia.middleware.msg.spi.MsgConsumerSpi;
import com.xxc.xia.sub.msg.consumer.LocalMsgConsumer;
import com.xxc.xia.sub.msg.consumer.MsgConsumeResult;
import com.xxc.xia.sub.msg.model.MsgInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/10 22:47
 */
@Service
@Slf4j
public class MsgConsumerSpiImpl implements MsgConsumerSpi {

    @Autowired
    private List<LocalMsgConsumer> localMsgConsumerList;

    @Override
    public void subscribe(Msg msg) {
        List<LocalMsgConsumer> consumerList = localMsgConsumerList.stream()
            .filter(x -> x.getMsgType().name().equals(msg.getMsgHeader().getMsgType())).toList();
        MsgInfo msgInfo = new MsgInfo();
        msgInfo.setMsgTypeEnum(MsgTypeEnum.getByName(msg.getMsgHeader().getMsgType()));
        msgInfo.setRetryCount(msg.getMsgHeader().getRetryCnt());
        msgInfo.setPayload(msg.getPayload());
        for (LocalMsgConsumer consumer : consumerList) {
            MsgConsumeResult msgConsumeResult = consumer.consume(msgInfo);
            if (!msgConsumeResult.isSuccess() && msgConsumeResult.isNeedRollback()) {
                log.error("消息处理失败，开始回滚");
                msg.getMsgHeader().setRollback(true);
            }
        }
    }
}
