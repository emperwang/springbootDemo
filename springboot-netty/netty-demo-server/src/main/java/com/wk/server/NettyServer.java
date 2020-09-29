package com.wk.server;

import com.wk.server.hander.NettyServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {

    @Value("${netty.port}")
    private Integer port;

    @Autowired
    private NettyServerHandlerInitializer nettyServerHandlerInitializer;

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    // netty server channel
    private Channel channel;

    // 初始函数
    @PostConstruct
    public void start() throws InterruptedException {
        final ServerBootstrap bootstrap = new ServerBootstrap();

        bootstrap.group(boss,worker)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG,1024)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .childHandler(nettyServerHandlerInitializer);

        final ChannelFuture future = bootstrap.bind().sync();
        if (future.isSuccess()){
            channel = future.channel();
            log.info("[start][netty server 启动在{} 端口]",port);
        }
    }

    // 销毁函数
    @PreDestroy
    public void shutdown(){
        if (channel != null){
            channel.close();
        }
        // 优雅关闭两个 eventLoopGroup 对象
        boss.shutdownGracefully();
        worker.shutdownGracefully();
    }
}
