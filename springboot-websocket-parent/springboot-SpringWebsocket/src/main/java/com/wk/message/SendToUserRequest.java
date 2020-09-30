package com.wk.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SendToUserRequest implements Message {
    public static final String TYPE = "SEND_TO_USER_REQEUST";

    private String msgId;

    private String content;
}
