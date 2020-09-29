package com.wk.client;

import com.wk.client.handler.NettyClientHandlerInitializer;
import com.wk.codec.Invocation;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class NettyClient {
    private static final Integer RECONNECT_SECONDS=20;

    @Value("${netty.server.host}")
    private String servetHost;
    @Value("${netty.server.port}")
    private Integer serverPort;

    @Autowired
    private NettyClientHandlerInitializer nettyClientHandlerInitializer;

    private EventLoopGroup eloop = new NioEventLoopGroup();
    // netty client channel
    private volatile Channel channel;

    @PostConstruct
    public void start(){
        final Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(eloop)
                .channel(NioSocketChannel.class)
                .remoteAddress(servetHost,serverPort)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(nettyClientHandlerInitializer);

        bootstrap.connect().addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                // 连接失败
                if (! channelFuture.isSuccess()){
                    log.error("[start][netty client 连接服务器 {}:{} 失败]", servetHost,serverPort);
                    reconnect();
                    return;
                }
                // 连接成功
                channel = channelFuture.channel();
                log.info("[start][Netty client 连接服务器( {}:{} )成功", servetHost,serverPort);
            }
        });
    }

    public void reconnect(){
        eloop.schedule(() -> {
            log.info("[reconnect][开始重连]");
            try{
                start();
            }catch (Exception e){
                log.error("[reconenct][重连失败]: {}",e.getCause());
            }
        },RECONNECT_SECONDS, TimeUnit.SECONDS);
        log.info("[reconnect][{} 秒后将发起重连]", RECONNECT_SECONDS);
    }

    @PreDestroy
    public void shutdown(){
        if (channel != null){
            channel.close();
        }
        eloop.shutdownGracefully();
    }

    // 发送消息
    public void send(Invocation invocation){
        if (channel == null){
            log.error("[send] 连接不存在");
            return;
        }
        if (!channel.isActive()){
            log.error("[send][连接 {} 未激活.", channel.id());
            return;
        }
        channel.writeAndFlush(invocation);
    }
}
