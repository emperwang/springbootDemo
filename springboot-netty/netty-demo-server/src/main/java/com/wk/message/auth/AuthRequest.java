package com.wk.message.auth;

import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 *  用户认证请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class AuthRequest implements Message {
    public static final String TYPE = "AUTH_REQUEST";
    /**
     * 认证token
     */
    private String accessToken;
}
