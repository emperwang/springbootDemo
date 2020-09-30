package com.websocket.web.controller;

import com.websocket.web.service.MySocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class SocketController {

    @Autowired
    private MySocket socket;

    @GetMapping("test.do")
    @ResponseBody
    public String helloTest(){

        return "hello test";
    }
    @GetMapping("first.do")
    public String first(){

        return "index";
    }
    @GetMapping("socket.do")
    public String testSocket(){
        return "socket";
    }

    @GetMapping("sendMsg.do")
    @ResponseBody
    public String sendMessageBySocket() throws IOException {
        socket.sendInfo("this is a test");
        return "ok";
    }
}
