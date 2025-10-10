package com.xxc.xia.middleware.msg.model;

import lombok.Data;

import java.io.Serializable;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:13
 */
@Data
public class MsgHeader implements Serializable {
    private String msgType;
    private int retryCnt;
    private boolean rollback;
}
