package com.wk.client.handler;

import com.wk.codec.InvocationDecoder;
import com.wk.codec.InvocationEncoder;
import com.wk.dispatcher.MessageDispatcher;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NettyClientHandlerInitializer extends ChannelInitializer<Channel> {

    /**
     *  心跳超时
     */
    private static final Integer READ_TIMEOUT_SECONDS = 60;

    @Autowired
    private MessageDispatcher messageDispatcher;
    @Autowired
    private NettyClientHandler nettyClientHandler;


    @Override
    protected void initChannel(Channel channel) throws Exception {
        channel.pipeline()
                // 超时检测
                .addLast(new IdleStateHandler(READ_TIMEOUT_SECONDS,0,0))
                .addLast(new ReadTimeoutHandler(3 *READ_TIMEOUT_SECONDS))
                // 编解码器
                .addLast(new InvocationEncoder())
                .addLast(new InvocationDecoder())
                // 消息分发器
                .addLast(messageDispatcher)
                // 客户端处理器
                .addLast(nettyClientHandler);
    }
}
