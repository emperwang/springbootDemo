package com.wk.message.auth;

import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *  认证响应
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class AuthResponse implements Message {

    public static final String TYPE = "AUTH_RESPONSE";
    // 响应状态码
    private Integer code;
    // 响应提示
    private String message;


}
