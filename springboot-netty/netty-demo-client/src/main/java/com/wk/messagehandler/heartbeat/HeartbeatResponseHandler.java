package com.wk.messagehandler.heartbeat;

import com.wk.dispatcher.MessageHandler;
import com.wk.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeartbeatResponseHandler implements MessageHandler<HeartbeatResponse> {
    @Override
    public void execute(Channel channel, HeartbeatResponse message) {
        log.info("[execute][收到连接 {} 的心跳响应", channel.id());
    }

    @Override
    public String getType() {
        return HeartbeatResponse.TYPE;
    }
}
