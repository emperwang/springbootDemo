package com.wk.handler;

import com.wk.message.Message;
import org.springframework.web.socket.WebSocketSession;


public interface MessageHandler<T extends Message> {
    /**
    * @author: wk
    * @Date: 2020/9/30 11:48
    * @Param   
    * @Return  
    * @Description  执行处理消息
    */
    void execute(WebSocketSession session, T message);
    
    /**
    * @author: wk
    * @Date: 2020/9/30 11:52
    * @Param
    * @Return  
    * @Description  返回消息类型
    */
    String getType();
}
