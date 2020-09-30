package com.wk.handler;

import com.wk.message.UserJoinNoticeReuqest;
import com.wk.message.auth.AuthRequest;
import com.wk.message.auth.AuthResponse;
import com.wk.util.WebSocketUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.Session;

/**
 * @author: wk
 * @Date: 2020/9/30 13:57
 * @Description
 */
@Component
public class AuthMessageHandler implements MessageHandler<AuthRequest> {
    @Override
    public void execute(WebSocketSession session, AuthRequest message) {
        if (StringUtils.isEmpty(message)){
            WebSocketUtil.send(session, AuthResponse.TYPE,
                    new AuthResponse().setCode(1).setMessage("认证accessToken未传入"));
            return;
        }
        // 添加到 util中
        WebSocketUtil.addSession(session,message.getAccessToken());
        // 判断是否认证成功. 这里默认直接成功
        WebSocketUtil.send(session,AuthResponse.TYPE,new AuthResponse().setCode(0));
        // 通知所有人,某个人加入了,这个是可选逻辑,仅仅是为了掩饰
        WebSocketUtil.broadcast(UserJoinNoticeReuqest.TYPE,
                new UserJoinNoticeReuqest().setNickname(message.getAccessToken()));
    }

    @Override
    public String getType() {
        return AuthRequest.TYPE;
    }
}
