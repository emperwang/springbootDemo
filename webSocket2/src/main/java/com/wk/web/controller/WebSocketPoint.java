package com.wk.web.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

// 参考:https://blog.csdn.net/liyongzhi1992/article/details/81221103
@Controller
public class WebSocketPoint {
    private static Logger log = LoggerFactory.getLogger(WebSocketPoint.class);
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    private Map<String,Integer> userMap = new HashMap<>();
    @RequestMapping("socketpoint.do")
    public String pointHello(){
        return "socketPoint";
    }

    @RequestMapping("pointsend.do")
    @ResponseBody
    public String sendTopicMessage(String name){
        log.info("send to user :"+name);
        messagingTemplate.convertAndSendToUser(name,"/queue/getResponse","point to point");
        return "send ok...";
    }

    @MessageMapping("/point")
    public String receiveMsg(@RequestBody String name, @Headers Map<String ,Object> headers){
        log.info("headers is:"+headers.toString());
        log.info("receive msg is:"+name);
        String name1 = JSON.parseObject(name).getString("name");
        userMap.put(name1,null);
        return "receive ok";
    }
}
