package com.wk.messagehandler.auth;

import com.wk.codec.Invocation;
import com.wk.dispatcher.MessageHandler;
import com.wk.message.auth.AuthRequest;
import com.wk.message.auth.AuthResponse;
import com.wk.server.NettyChannelManager;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Slf4j
@Component
public class AuthRequestHandler implements MessageHandler<AuthRequest> {

    @Autowired
    private NettyChannelManager nettyChannelManager;

    @Override
    public void execute(Channel channel, AuthRequest message) {
        //  如果未传递  accessToken
        if (StringUtils.isEmpty(message.getAccessToken())){
            final AuthResponse authResponse = new AuthResponse().setCode(1).setMessage("认证token未传递");
            channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
            return;
        }
        // 将用户和channel绑定
        // 考虑到代码简化,先直接使用accessToken 作为suer
        nettyChannelManager.addUser(channel, message.getAccessToken());
        // 响应认证成功
        final AuthResponse authResponse = new AuthResponse().setCode(0);
        channel.writeAndFlush(new Invocation(AuthResponse.TYPE, authResponse));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
