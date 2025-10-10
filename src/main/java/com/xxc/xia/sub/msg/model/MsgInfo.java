package com.xxc.xia.sub.msg.model;

import com.xxc.xia.common.enums.MsgTypeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/10 22:52
 */
@Data
public class MsgInfo implements Serializable {
    private MsgTypeEnum msgTypeEnum;

    private int         retryCount;

    private Object      payload;
}
