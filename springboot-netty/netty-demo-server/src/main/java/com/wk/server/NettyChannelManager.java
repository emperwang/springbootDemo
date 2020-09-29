package com.wk.server;

import com.wk.codec.Invocation;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  客户端channel 管理器. 提供两种功能
 *  1. 客户端 channel的管理
 *  2. 向客户端channel发送消息
 */
@Component
@Slf4j
public class NettyChannelManager {
    // channel@attr(AttributeKey) 属性中, 表示channel对应的用户
    private static final AttributeKey<String> CHANNEL_ATTR_KEY_USER=AttributeKey.newInstance("user");
    // channel映射关系
    private ConcurrentHashMap<ChannelId, Channel> channels = new ConcurrentHashMap<>();

    /**
     * 用户与channel的映射
     * 通过它, 可以获取用户对应的channel. 这样,我们可以向指定用户发送消息
     */
    private ConcurrentHashMap<String, Channel> userChannels = new ConcurrentHashMap<>();

    /**
     *  添加channel 到channels中
     * @param channel
     */
    public void add(Channel channel){
        channels.put(channel.id(), channel);
        log.info("[add][一个连接{} 加入]", channel.id());
    }

    /**
     * 添加指定用户到 userChannels中
     */
    public void addUser(Channel channel, String user){
        final Channel existChannel = channels.get(channel.id());
        if (existChannel == null){
            log.error("[addUser][连接 {} 不存在", channel.id());
            return;
        }
        // 设置属性
        channel.attr(CHANNEL_ATTR_KEY_USER).set(user);
        // 添加到userChannels中
        userChannels.put(user,channel);
    }

    /**
     * 将channel从 channels 和 userchannels中移除
     * @param channel
     */
    public void remove(Channel channel){
        // 移除channels
        channels.remove(channel.id());
        // 移除 userChannels
        if (channel.hasAttr(CHANNEL_ATTR_KEY_USER)){
            userChannels.remove(channel.attr(CHANNEL_ATTR_KEY_USER).get());
        }
        log.info("[remove][一个连接 {} 离开]", channel.id());
    }

    /**
     *  向指定用户发送消息
     * @param user
     * @param invocation
     */
    public void send(String user, Invocation invocation){
        // 获得用户对应的channel
        final Channel channel = userChannels.get(user);
        if (channel == null){
            log.error("[send] [连接不存在]");
            return;
        }

        if (!channel.isActive()){
            log.error("[send][连接 {} 未激活]", channel.id());
            return;
        }
        // 发送消息
        channel.writeAndFlush(invocation);
    }

    /**
     * 向所有用户发送消息
     */
    public void sendAll(Invocation invocation){
        for (Channel channel : channels.values()) {
            if (! channel.isActive()){
                log.error("[send][连接 {} 未激活]", channel.id());
                continue;
            }
            channel.writeAndFlush(invocation);
        }
    }
}





















