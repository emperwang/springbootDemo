package com.wk.messagehandler.auth;

import com.wk.dispatcher.MessageHandler;
import com.wk.message.auth.AuthResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class AuthResponseHandler implements MessageHandler<AuthResponse> {

    @Override
    public void execute(Channel channel, AuthResponse message) {
        log.info("[execute][认证结果: {}]", message);
    }

    @Override
    public String getType() {
        return AuthResponse.TYPE;
    }
}
