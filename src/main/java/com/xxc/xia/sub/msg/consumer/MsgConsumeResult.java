package com.xxc.xia.sub.msg.consumer;

import lombok.Data;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:08
 */
@Data
public class MsgConsumeResult {
    private boolean success;

    private boolean needRollback = true;

}
