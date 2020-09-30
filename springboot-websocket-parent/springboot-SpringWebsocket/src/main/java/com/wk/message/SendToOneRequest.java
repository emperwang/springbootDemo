package com.wk.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SendToOneRequest implements Message{

    public static final String TYPE="SEND_TO_ONE_REQUEST";

    private String toUser;
    private String msgId;
    private String content;
}
