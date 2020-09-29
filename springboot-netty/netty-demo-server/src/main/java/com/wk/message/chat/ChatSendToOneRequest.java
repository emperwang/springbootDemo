package com.wk.message.chat;

import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class ChatSendToOneRequest implements Message {

    public static final String TYPE = "CHAR_SEND_TO_ONE_REQUEST";
    // 发送给的用户
    private String toUser;
    // 消息编号
    private String msgId;
    // 内容
    private String context;
}
