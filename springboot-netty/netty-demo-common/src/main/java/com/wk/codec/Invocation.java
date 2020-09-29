package com.wk.codec;

import com.alibaba.fastjson.JSON;
import com.wk.dispatcher.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通信协议的消息体
 */
@Data
@NoArgsConstructor
public class Invocation {
    // 消息类型
    private String type;
    // 消息,json格式
    private String message;

    public Invocation(String type, String message){
        this.type = type;
        this.message = message;
    }

    public Invocation(String type, Message message){
        this.type = type;
        this.message = JSON.toJSONString(message);
    }
}
