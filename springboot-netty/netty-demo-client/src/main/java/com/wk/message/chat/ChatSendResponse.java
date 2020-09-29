package com.wk.message.chat;

import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 *  聊天发送消息结果的 response
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChatSendResponse implements Message {

    public static final String TYPE = "CHAT_SEND_RESPONSE";
    //  消息编号
    private String msgId;
    // 响应状态码
    private Integer code;
    // 响应提示
    private String message;

}
