package com.wk.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.CorruptedFrameException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class InvocationDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        // 标记当前读位置
        in.markReaderIndex();
        // 判断是否能够读取length 长度
        if (in.readableBytes() <= 4){
            return;
        }
        // 读取长度
        final int length = in.readInt();
        if (length < 0){
            throw new CorruptedFrameException("negative length");
        }
        // 如果message 不够,则退回到原来读取的位置
        if (in.readableBytes() < length){
            in.resetReaderIndex();
            return;
        }
        // 读取内容
        byte[] context = new byte[length];
        in.readBytes(context);

        // 解析成 Invocation
        Invocation invocation = (Invocation)JSON.parseObject(context, Invocation.class);
        out.add(invocation);
        log.info("[decode][连接 {} 解析到一条消息 {}]", channelHandlerContext.channel().id(), invocation.toString());
    }
}
