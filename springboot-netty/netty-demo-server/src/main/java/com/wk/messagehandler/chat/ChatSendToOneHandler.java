package com.wk.messagehandler.chat;

import com.wk.codec.Invocation;
import com.wk.dispatcher.MessageHandler;
import com.wk.message.chat.ChatRedirectToUserRequest;
import com.wk.message.chat.ChatSendResponse;
import com.wk.message.chat.ChatSendToOneRequest;
import com.wk.server.NettyChannelManager;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
public class ChatSendToOneHandler implements MessageHandler<ChatSendToOneRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToOneRequest message) {
        // 直接发送成功
        final ChatSendResponse response = new ChatSendResponse().setMsgId(message.getMsgId()).setCode(0);
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));

        final ChatRedirectToUserRequest userRequest = new ChatRedirectToUserRequest().setMsgId(message.getMsgId())
                .setContext(message.getContext());
        nettyChannelManager.send(message.getToUser(), new Invocation(ChatRedirectToUserRequest.TYPE,userRequest));
    }

    @Override
    public String getType() {
        return ChatSendToOneRequest.TYPE;
    }
}
