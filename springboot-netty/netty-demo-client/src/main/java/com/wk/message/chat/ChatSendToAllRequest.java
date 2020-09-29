package com.wk.message.chat;

import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *  发送给所有人的群聊消息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Accessors(chain = true)
public class ChatSendToAllRequest implements Message {
    public static final String TYPE = "CHAT_SEND_TO_ALL_REQUEST";

    private String msgId;

    private String content;

}
