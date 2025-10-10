package com.xxc.xia.middleware.msg;

import com.xxc.xia.middleware.msg.model.Msg;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * desc..
 *
 * @author xxc
 * @version 2025/10/8 23:22
 */
@Service
@Slf4j
public class MsgCenter implements DisposableBean {
    public static final LinkedBlockingQueue<Msg> MSG_QUEUE  = new LinkedBlockingQueue<>(200);

    public static final ExecutorService          EXECUTOR   = Executors.newFixedThreadPool(1);
    public static final AtomicBoolean            isShutdown = new AtomicBoolean(false);

    @Override
    public void destroy() throws Exception {
        isShutdown.set(true);
        EXECUTOR.shutdown();
    }
}
