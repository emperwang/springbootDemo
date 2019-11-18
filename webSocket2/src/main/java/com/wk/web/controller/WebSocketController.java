package com.wk.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WebSocketController {
    private static Logger log = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @RequestMapping("send.do")
    @ResponseBody
    public String sendTopicMessage(){
        messagingTemplate.convertAndSend("/topic/getResponse","aa,bb,cc");
        return "send ok...";
    }

    @MessageMapping("/welcome")
    public String receiveMsg(String name, @Headers Map<String ,Object> headers){
        log.info("headers is:"+headers.toString());
        log.info("receive msg is:"+name);
        return "receive ok";
    }
    @MessageMapping("/welcomeSend")
    //SendTo 此操作是: 有数据发送到welcomeSend时,数据会被发送到/topic/getResponse此目的
    @SendTo("/topic/getResponse")
    public String revAndSend(String name){
        log.info("receive msg is:"+name);

        return "welcome ,"+name;
    }
}
