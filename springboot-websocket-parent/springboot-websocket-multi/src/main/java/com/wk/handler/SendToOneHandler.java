package com.wk.handler;

import com.wk.message.SendResponse;
import com.wk.message.SendToOneRequest;
import com.wk.message.SendToUserRequest;
import com.wk.util.WebSocketUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.Session;

/**
 * @author: wk
 * @Date: 2020/9/30 14:08
 * @Description
 */
@Slf4j
@Component
public class SendToOneHandler implements MessageHandler<SendToOneRequest> {
    @Override
    public void execute(Session session, SendToOneRequest message) {
        // 直接假装验证成功
        final SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session,SendResponse.TYPE, sendResponse);

        // 创建转发的消息
        final SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId())
                .setContent(message.getContent());
        // 广播发送
        WebSocketUtil.send(message.getToUser(),SendToUserRequest.TYPE, sendToUserRequest);
    }

    @Override
    public String getType() {
        return SendToOneRequest.TYPE;
    }
}
