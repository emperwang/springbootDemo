package com.wk.message.auth;

import com.wk.message.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class AuthResponse implements Message {
    public static final String TYPE="AUTH_RESPONSE";

    private Integer code;

    private String message;
}
