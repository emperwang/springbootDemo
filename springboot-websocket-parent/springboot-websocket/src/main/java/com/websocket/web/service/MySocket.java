package com.websocket.web.service;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicLong;


/**
 *
 * Author:wk
 * Time:2018.11.4
 */
@Component
@ServerEndpoint(value = "/websocket")
public class MySocket {
    //记录当前在线连接数
    private static AtomicLong onlineCount = new AtomicLong(0);
    //存放每个客户端对应的MySocket对象
    private static CopyOnWriteArraySet<MySocket> webSocketSet = new CopyOnWriteArraySet<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送
    private Session session;

    //建立连接的回调函数
    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("有新链接加入，当前在线人数为:"+onlineCount.incrementAndGet());;
        sendMessage("open session");
    }
    //关闭连接调用方法
    @OnClose
    public void onClose(){
        webSocketSet.remove(this);
        System.out.println("有一连接关闭,当前在线人数为:"+onlineCount.decrementAndGet());;
    }
    //接收消息回调函数
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的消息:"+message);
    }
    //发送错误时调用
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    //发送消息
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    //群发消息
    public static void sendInfo(String message) throws IOException {
        for(MySocket socket:webSocketSet){
            socket.sendMessage(message);
        }
    }
}
