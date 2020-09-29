package com.wk.client.handler;

import com.wk.client.NettyClient;
import com.wk.codec.Invocation;
import com.wk.message.heartbeat.HeartbeatRequest;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    @Autowired
    private NettyClient nettyClient;

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 发起重连
        nettyClient.reconnect();
        // 继续触发事件
        super.channelInactive(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        log.error("[exceptionCaught][连接 {} 发生异常]",ctx.channel().id());
        ctx.channel().close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 空闲时, 向服务器发送一次心跳
        if (evt instanceof IdleStateEvent){
            log.info("[userEventTriggered][发起一次心跳]");
            final HeartbeatRequest request = new HeartbeatRequest();
            ctx.channel().writeAndFlush(new Invocation(HeartbeatRequest.TYPE,
                    request));
        }else{
            super.userEventTriggered(ctx,evt);
        }
    }
}
