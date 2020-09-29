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
public class ChatRedirectToUserRequest implements Message {
    public static final String TYPE = "CHAT_REDIRECT_TO_USER_REQUEST";
    // 消息编号
    private String msgId;
    // 消息内容
    private String context;

}
