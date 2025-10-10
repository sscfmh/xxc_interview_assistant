package com.xxc.xia.middleware.msg.model;

import lombok.Data;

import java.io.Serializable;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:24
 */
@Data
public class Msg implements Serializable {
    private MsgHeader msgHeader;
    private Object    payload;
}
