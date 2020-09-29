package com.wk.messagehandler.chat;

import com.wk.codec.Invocation;
import com.wk.dispatcher.MessageHandler;
import com.wk.message.chat.ChatRedirectToUserRequest;
import com.wk.message.chat.ChatSendResponse;
import com.wk.message.chat.ChatSendToAllRequest;
import com.wk.server.NettyChannelManager;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChatSendToAllHandler implements MessageHandler<ChatSendToAllRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, ChatSendToAllRequest message) {
        // 直接成功
        final ChatSendResponse response = new ChatSendResponse().setMsgId(message.getMsgId()).setCode(0);
        channel.writeAndFlush(new Invocation(ChatSendResponse.TYPE, response));

        // 创建转发消息, 并广播转发
        final ChatRedirectToUserRequest redirectToUserRequest = new ChatRedirectToUserRequest().setMsgId(message.getMsgId())
                .setContext(message.getContent());
        nettyChannelManager.sendAll(new Invocation(ChatRedirectToUserRequest.TYPE, redirectToUserRequest));
    }

    @Override
    public String getType() {
        return ChatSendToAllRequest.TYPE;
    }
}
