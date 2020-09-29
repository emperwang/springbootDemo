package com.wk.message.heartbeat;

import com.wk.dispatcher.Message;
import lombok.ToString;

@ToString
public class HeartbeatRequest implements Message {
    /**
     * 心跳请求
     */
    public static final String TYPE = "HEARTBEAT_REQUEST";
}
