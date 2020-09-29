package com.wk.dispatcher;

import com.alibaba.fastjson.JSON;
import com.wk.codec.Invocation;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@ChannelHandler.Sharable
public class MessageDispatcher extends SimpleChannelInboundHandler<Invocation> {

    @Autowired
    private MessageHandlerContainer messageHandlerContainer;

    private final ExecutorService service = Executors.newFixedThreadPool(100);
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Invocation o) throws Exception {
        // 获得type 对应的messageHandler 处理器
        MessageHandler messageHandler = messageHandlerContainer.getMessageHandler(o.getType());
        // 获得 messageHandler 处理器的消息类
        Class<? extends Message> messageClass = MessageHandlerContainer.getMessageClass(messageHandler);
        // 解析消息
        Message message = JSON.parseObject(o.getMessage(), messageClass);

        // 执行逻辑
        /*service.submit(() ->{
            messageHandler.execute(channelHandlerContext.channel(),message);
        });*/
        messageHandler.execute(channelHandlerContext.channel(),message);
    }
}
