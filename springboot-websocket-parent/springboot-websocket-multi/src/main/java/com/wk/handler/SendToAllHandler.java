package com.wk.handler;

import com.wk.message.SendResponse;
import com.wk.message.SendToAllRequest;
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
public class SendToAllHandler implements MessageHandler<SendToAllRequest> {
    @Override
    public void execute(Session session, SendToAllRequest message) {
        // 假装成功
        final SendResponse sendResponse = new SendResponse().setMsgId(message.getMsgId()).setCode(0);
        WebSocketUtil.send(session,SendResponse.TYPE,sendResponse);
        // 创建转发的消息
        final SendToUserRequest sendToUserRequest = new SendToUserRequest().setMsgId(message.getMsgId()).setContent(message.getContent());
        // 广播发送
        WebSocketUtil.broadcast(SendToUserRequest.TYPE, sendToUserRequest);

    }

    @Override
    public String getType() {
        return SendToAllRequest.TYPE;
    }
}
