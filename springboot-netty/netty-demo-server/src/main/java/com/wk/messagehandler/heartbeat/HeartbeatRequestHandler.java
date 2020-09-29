package com.wk.messagehandler.heartbeat;

import com.wk.codec.Invocation;
import com.wk.dispatcher.MessageHandler;
import com.wk.message.heartbeat.HeartbeatRequest;
import com.wk.message.heartbeat.HeartbeatResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HeartbeatRequestHandler implements MessageHandler<HeartbeatRequest> {

    @Override
    public void execute(Channel channel, HeartbeatRequest message) {
        log.info("[execute][收到连接 {} 的心跳请求]", channel.id());
        // 响应心跳
        final HeartbeatResponse response = new HeartbeatResponse();
        channel.writeAndFlush(new Invocation(HeartbeatResponse.TYPE, response));
    }

    @Override
    public String getType() {
        return HeartbeatRequest.TYPE;
    }
}
