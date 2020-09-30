package com.wk.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserJoinNoticeReuqest implements Message {

    public static final String TYPE = "USER_JOIN_NOTICE_REQUEST";

    private String nickname;
}
