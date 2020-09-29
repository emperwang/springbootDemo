package com.wk.dispatcher;

import io.netty.channel.Channel;

public interface MessageHandler<T extends Message> {

    /**
     *  执行处理消息
     * @param channel 通道
     * @param message 消息
     */
    void execute(Channel channel, T message);

    /**
     *  返回消息类型, 即每个Message 实现类上的TYPE 静态字段
     * @return
     */
    String getType();
}
