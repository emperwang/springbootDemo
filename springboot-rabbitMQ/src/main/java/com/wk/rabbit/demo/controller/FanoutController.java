package com.wk.rabbit.demo.controller;

import com.wk.rabbit.demo.fanout.send.FanoutSender;
import com.wk.rabbit.demo.topic.sender.TopicSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FanoutController {

    @Autowired
    private FanoutSender fanoutSender;

    @Autowired
    private TopicSender topicSender;

    @GetMapping("fansend.do")
    @ResponseBody
    public String testFanoutSend(){
        fanoutSender.sendMsg();
        return "fanout send success";
    }

    @GetMapping("topic.do")
    @ResponseBody
    public String testTopic(){
        topicSender.sendMsg();
        return "topic send success";
    }
}
