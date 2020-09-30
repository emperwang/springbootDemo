package com.wk.util;

import com.alibaba.fastjson.JSONObject;
import com.wk.message.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: wk
 * @Date: 2020/9/30 11:54
 * @Description  websocket工具类, 提供客户端连接的管理功能
 */
@Slf4j
public class WebSocketUtil {

    /**
    * @author: wk
    * @Description session 与用户端的映射
    */
    private static final Map<WebSocketSession,String> SESSION_USER_MAP = new ConcurrentHashMap<>();

    private static final Map<String,WebSocketSession> USER_SESSION_MAP = new ConcurrentHashMap<>();
    
    /**
    * @author: wk
    * @Date: 2020/9/30 11:56
    * @Param
    * @Return
    * @Description 添加session, 在这里方法中,会添加用户和session之间的映射
    */
    public static void addSession(WebSocketSession session,String user){
        USER_SESSION_MAP.put(user, session);

        SESSION_USER_MAP.put(session,user);
    }
    /**
    * @author: wk
    * @Date: 2020/9/30 11:57
    * @Param
    * @Return
    * @Description 移除session
    */
    public static void removeSession(WebSocketSession session){
        final String user = SESSION_USER_MAP.remove(session);
        if (user != null && user.length() > 0){
            USER_SESSION_MAP.remove(user);
        }
    }

    /**
    * @author: wk
    * @Date: 2020/9/30 11:58
    * @Description 广播发送消息给所有在线用户
    */
    public static <T extends Message> void broadcast(String type, T message){
        final TextMessage msgText = buildTextMessage(type, message);
        for (WebSocketSession session : SESSION_USER_MAP.keySet()) {
            sendTextMessage(session, msgText);
        }
    }
    /**
    * @author: wk
    * @Date: 2020/9/30 13:34
    * @Param   
    * @Return  
    * @Description   发送消息给单个用户的session
    */
    public static <T extends Message> void send(WebSocketSession session, String type, T messsage){
        final TextMessage textMessage = buildTextMessage(type, messsage);
        sendTextMessage(session,textMessage);
    }
    /**
    * @author: wk
    * @Date: 2020/9/30 13:36
    * @Param
    * @Return
    * @Description 发送消息给指定用户
    */
    public static <T extends Message> boolean send(String user, String type, T message){
        final WebSocketSession session = USER_SESSION_MAP.get(user);
        if (session == null){
            log.error("[send][user:{} 不存在对应的session]",user);
            return false;
        }
        send(session,type,message);
        return true;
    }
    // 真正的发送消息操作
    private static void sendTextMessage(WebSocketSession session, TextMessage msg){
        if (session == null){
            log.error("[sendTextMessage][session 为null]");
            return;
        }
        try{
            session.sendMessage(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
    * @author: wk
    * @Date: 2020/9/30 12:00
    * @Description 构建完整的消息
    */
    private static <T extends Message> TextMessage buildTextMessage(String type,T message){
        final JSONObject msg = new JSONObject();
        msg.put("type",type);
        msg.put("body", message);
        return new TextMessage(msg.toJSONString());
    }
}
