package com.wk.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SendResponse implements Message{
    public static final String TYPE = "SEND_RESPONSE";

    private String msgId;
    private Integer code;
    private String message;
}
