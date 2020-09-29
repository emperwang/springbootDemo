package com.wk.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvocationEncoder extends MessageToByteEncoder<Invocation> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Invocation invocation, ByteBuf out) throws Exception {
        //  将 invocation 转换为 byte[] 数组
        final byte[] context = JSON.toJSONBytes(invocation);
        // 写入length
        out.writeInt(context.length);
        // 写入内容
        out.writeBytes(context);
        log.info("[encode][连接 {} 编写了一条消息 :{}", channelHandlerContext.channel().id(),invocation.toString());
    }

}
