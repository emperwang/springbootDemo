package com.wk.controller;

import com.wk.constants.ThreadContainer;
import com.wk.job.ReceiveConnect;
import com.wk.job.ServerSimulator;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class SocketController {

    @Autowired
    private ReceiveConnect receiveConnect;

    @GetMapping(value = "startRev")
    public String startRev(){
        Thread thread = ThreadContainer.threads.get(ThreadContainer.Rev);
        if (thread != null && thread.isAlive()){
            log.info("rev thread is running.");
        }else {
            ThreadContainer.threads.put(ThreadContainer.Rev,receiveConnect);
            receiveConnect.start();
        }
        return "start already";
    }
    @GetMapping(value = "stopRev")
    public String stopRev(){
        ReceiveConnect receiveConnect = (ReceiveConnect) ThreadContainer.threads.get(ThreadContainer.Rev);
        if (receiveConnect != null && receiveConnect.isAlive()){
            log.info("stop method,rev thread is running.");
            receiveConnect.stopRun();
        }else {
            log.info("rev thread isn't running.");
        }

        return "stop already";
    }

    @GetMapping(value = "statusRev")
    public String statusRev(){
        ReceiveConnect receiveConnect = (ReceiveConnect) ThreadContainer.threads.get(ThreadContainer.Rev);
        if (receiveConnect != null && receiveConnect.isAlive()){
            log.info("stop method,rev thread is running.");
            return "thread status: "+receiveConnect.isAlive();
        }else {
            log.info("rev thread isn't running.");
            return "thread not running";
        }
    }

    @GetMapping(value = "getSize")
    public String getMapSize(){
        return ThreadContainer.threads.size()+"";
    }

    @GetMapping(value = "client")
    public String statusClient(){
        ServerSimulator simulator = (ServerSimulator) ThreadContainer.threads.get("test");

        return simulator.isAlive() + "";
    }

    @GetMapping(value = "send")
    public String sendDate(){
        ServerSimulator simulator = (ServerSimulator) ThreadContainer.threads.get("test");
        if (simulator != null && simulator.isAlive()){
            try {
                simulator.writeDate();
            } catch (IOException e) {
                log.error("sendDate error, msg:{}",e.getMessage());
            }
        }else{
            return "client not running";
        }
        return "send already";
    }

    @GetMapping(value = "send2")
    public String sendDate2(){
        ServerSimulator simulator = (ServerSimulator) ThreadContainer.threads.get("test");
        if (simulator != null && simulator.isAlive()){
            try {
                simulator.writeDate2();
            } catch (IOException e) {
                log.error("sendDate2 error, msg:{}",e.getMessage());
            }
        }else{
            return "client2 not running";
        }
        return "send2 already";
    }
}
