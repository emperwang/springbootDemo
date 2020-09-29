package com.wk.messagehandler.chat;

import com.wk.dispatcher.MessageHandler;
import com.wk.message.chat.ChatRedirectToUserRequest;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ChatRedirectToUserRequestHandler implements MessageHandler<ChatRedirectToUserRequest> {
    @Override
    public void execute(Channel channel, ChatRedirectToUserRequest message) {
        log.info("[execute][收到消息: {}]", message);
    }

    @Override
    public String getType() {
        return ChatRedirectToUserRequest.TYPE;
    }
}
